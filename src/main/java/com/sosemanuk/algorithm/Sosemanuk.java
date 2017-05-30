package com.sosemanuk.algorithm;

import com.sosemanuk.gui.MainWindow;
import com.sosemanuk.utils.Converter;
import com.sosemanuk.utils.Formatter;
import com.sosemanuk.utils.Stoper;

public class Sosemanuk {

    private int[][] subKeys = new int[25][4];

    private byte[] pInitialValue = new byte[16];

    private int[] s = new int[10];

    private int[] f = new int[4];

    private int[] data = new int[4];

    private int R1 = 0;

    private int R2 = 0;

    public void start(byte[] key, byte[] initialValue) {
        keySchedule(key);
        setInitialValue(initialValue);
        serpent24();
        workflow();
    }

    public static byte[] getKey(byte[] inputKey) {
        if (inputKey.length < 0 || inputKey.length > 32) {
            MainWindow.print("INPUT KEY should be longer/shorter");
        }
        return inputKey.length == 32 ? inputKey : expandKeyTo32Bytes(inputKey);
    }

    private static byte[] expandKeyTo32Bytes(byte[] key) {
        byte[] expandedKey = new byte[32];
        System.arraycopy(key, 0, expandedKey, 0, key.length);
        expandedKey[key.length] = 0x01;
        return addFollowingZeros(expandedKey, key.length + 1);
    }

    private static byte[] addFollowingZeros(byte[] array, int startPosition) {
        for (int i = startPosition; i < array.length; i++) {
            array[i] = 0x00;
        }
        return array;
    }

    private void keySchedule(byte[] key) {
        int[] w = new int[100];

        for (int i = 0; i < 8; i++) {
            w[i] = Converter.convertToInt(key, i * 4);
        }

        w[0] = Integer.rotateLeft(w[0] ^ w[3] ^ w[5] ^ w[7] ^ 0x9e3779b9, 11);
        w[1] = Integer.rotateLeft(w[1] ^ w[4] ^ w[6] ^ w[0] ^ 0x9e3779b9 ^ 1, 11);
        w[2] = Integer.rotateLeft(w[2] ^ w[5] ^ w[7] ^ w[1] ^ 0x9e3779b9 ^ 2, 11);
        w[3] = Integer.rotateLeft(w[3] ^ w[6] ^ w[0] ^ w[2] ^ 0x9e3779b9 ^ 3, 11);
        w[4] = Integer.rotateLeft(w[4] ^ w[7] ^ w[1] ^ w[3] ^ 0x9e3779b9 ^ 4, 11);
        w[5] = Integer.rotateLeft(w[5] ^ w[0] ^ w[2] ^ w[4] ^ 0x9e3779b9 ^ 5, 11);
        w[6] = Integer.rotateLeft(w[6] ^ w[1] ^ w[3] ^ w[5] ^ 0x9e3779b9 ^ 6, 11);
        w[7] = Integer.rotateLeft(w[7] ^ w[2] ^ w[4] ^ w[6] ^ 0x9e3779b9 ^ 7, 11);

        for (int i = 8; i < 100; i++) {
            w[i] = Integer.rotateLeft(w[i - 8] ^ w[i - 5] ^ w[i - 3] ^ w[i - 1] ^ 0x9e3779b9 ^ i, 11);
        }

        int sBoxNumber;
        for (int i = 0; i < 25; i++) {
            sBoxNumber = 7 - (i + 4) % 8;
            subKeys[i] = SerpentBitsliceSBox.sBox(sBoxNumber, w[4 * i], w[4 * i + 1], w[4 * i + 2], w[4 * i + 3], w[4 * i]);
        }
    }

    private void setInitialValue(byte[] initialValue) {
        if (initialValue.length > 16) {
            MainWindow.print("bad INITIAL VALUE length: " + initialValue.length);
            return;
        }

        if (initialValue.length == 16) {
            pInitialValue = initialValue;
            return;
        }

        System.arraycopy(initialValue, 0, pInitialValue, 0, initialValue.length);
        pInitialValue = addFollowingZeros(pInitialValue, initialValue.length);
    }

    private void serpent24() {
        for (int i = 0; i < 4; i++) {
            data[i] = Converter.convertToInt(pInitialValue, i * 4);
        }

        for (int i = 0; i < 24; i++) {
            serpentRound(i);

            if (i == 11) {
                s[6] = data[3];
                s[7] = data[2];
                s[8] = data[1];
                s[9] = data[0];
            }

            if (i == 17) {
                s[4] = data[1];
                s[5] = data[3];
                R1 = data[0];
                R2 = data[2];
            }
        }

        s[3] = subKeys[24][0] ^ data[0];
        s[2] = subKeys[24][1] ^ data[1];
        s[1] = subKeys[24][2] ^ data[2];
        s[0] = subKeys[24][3] ^ data[3];
    }

    private void serpentRound(int index) {
        for (int i = 0; i < 4; i++) {
            data[i] = subKeys[index][i] ^ data[i];
        }

        index = index % 8;
        data = SerpentBitsliceSBox.sBox(index, data[0], data[1], data[2], data[3], data[0]);
        data[0] = Integer.rotateLeft(data[0], 13);
        data[2] = Integer.rotateLeft(data[2], 3);
        data[1] = data[1] ^ data[0] ^ data[2];
        data[3] = data[3] ^ data[2] ^ (data[0] << 3);
        data[1] = Integer.rotateLeft(data[1], 1);
        data[3] = Integer.rotateLeft(data[3], 7);
        data[0] = data[0] ^ data[1] ^ data[3];
        data[2] = data[2] ^ data[3] ^ (data[1] << 7);
        data[0] = Integer.rotateLeft(data[0], 5);
        data[2] = Integer.rotateLeft(data[2], 22);
    }

    private void workflow() {
        byte[][] output = new byte[40][4];
        for (int k = 0; k < 10; k++) {
            int[] sOld = new int[]{s[(4 * k) % 10], s[(4 * k + 1) % 10], s[(4 * k + 2) % 10], s[(4 * k + 3) % 10]};

            for (int i = 0; i < 4; i++) {
                FSMstep(4 * k + i);
                LFSRstep(4 * k + i);
            }

            int[] z = SerpentBitsliceSBox.sb2(f[0], f[1], f[2], f[3], f[0]);

            for (int i = 0; i < 4; i++) {
                output[4 * k + i] = Converter.convertToByte(z[i] ^ sOld[i]);
            }
        }

        Stoper.stop();
        MainWindow.print("Time: " + Stoper.getTime() + " miliseconds\n");
        Formatter.printResult(output);
    }

    private void FSMstep(int t) {
        int temp = R1;
        R1 = (R2 + ((R1 & 0x01) == 0 ? s[(t + 1) % 10] : s[(t + 1) % 10] ^ s[(t + 8) % 10]));
        R2 = Integer.rotateLeft(temp * 0x54655307, 7);
        f[t % 4] = (s[(t + 9) % 10] + R1) ^ R2;
    }

    private void LFSRstep(int t) {
        s[(t + 10) % 10] = s[(t + 9) % 10] ^ (s[(t + 3) % 10] >>> 8) ^ (AlphaOperations.divisionByAlpha[s[(t + 3) % 10] & 0xFF])
                ^ (s[t % 10] << 8) ^ (AlphaOperations.muliplicationByAlpha[s[t % 10] >>> 24]);
    }
}
