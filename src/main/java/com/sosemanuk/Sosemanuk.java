package com.sosemanuk;

class Sosemanuk {

    private int[][] subKeys = new int[25][4];

    private byte[] piv = new byte[16];

    private int[] data = new int[4];

    private int[] s = new int[10];

    private int R1 = 0;

    private int R2 = 0;

    private int[] f = new int[4];

    void start(byte[] key, byte[] iv) {
        keySchedule(key);
        setInitialValue(iv);
        serpent24();
        workflow();
    }

    byte[] expandKey(byte[] key) {
        byte[] expandedKey = new byte[32];
        System.arraycopy(key, 0, expandedKey, 0, key.length);
        expandedKey[key.length] = 0x01;

        for (int i = key.length + 1; i < 32; i++) {
            expandedKey[i] = 0x00;
        }

        return expandedKey;
    }

    private void keySchedule(byte[] key) {
        int w0, w1, w2, w3, w4, w5, w6, w7;

        w0 = convertToInt(key, 0);
        w1 = convertToInt(key, 4);
        w2 = convertToInt(key, 8);
        w3 = convertToInt(key, 12);
        w4 = convertToInt(key, 16);
        w5 = convertToInt(key, 20);
        w6 = convertToInt(key, 24);
        w7 = convertToInt(key, 28);

        int[] w = new int[100];
        w0 = Integer.rotateLeft(w0 ^ w3 ^ w5 ^ w7 ^ 0x9e3779b9, 11);
        w1 = Integer.rotateLeft(w1 ^ w4 ^ w6 ^ w0 ^ 0x9e3779b9 ^ 1, 11);
        w2 = Integer.rotateLeft(w2 ^ w5 ^ w7 ^ w1 ^ 0x9e3779b9 ^ 2, 11);
        w3 = Integer.rotateLeft(w3 ^ w6 ^ w0 ^ w2 ^ 0x9e3779b9 ^ 3, 11);
        w4 = Integer.rotateLeft(w4 ^ w7 ^ w1 ^ w3 ^ 0x9e3779b9 ^ 4, 11);
        w5 = Integer.rotateLeft(w5 ^ w0 ^ w2 ^ w4 ^ 0x9e3779b9 ^ 5, 11);
        w6 = Integer.rotateLeft(w6 ^ w1 ^ w3 ^ w5 ^ 0x9e3779b9 ^ 6, 11);
        w7 = Integer.rotateLeft(w7 ^ w2 ^ w4 ^ w6 ^ 0x9e3779b9 ^ 7, 11);
        w[0] = w0;
        w[1] = w1;
        w[2] = w2;
        w[3] = w3;
        w[4] = w4;
        w[5] = w5;
        w[6] = w6;
        w[7] = w7;

        for (int i = 8; i < 100; i++) {
            w[i] = Integer.rotateLeft(w[i - 8] ^ w[i - 5] ^ w[i - 3] ^ w[i - 1] ^ 0x9e3779b9 ^ i, 11);
        }

        int sBoxNumber;
        for (int i = 0; i < 25; i++) {
            sBoxNumber = 7 - (i + 4) % 8;
            subKeys[i] = SerpentBitsliceSBox.sBox(sBoxNumber, w[4 * i], w[4 * i + 1], w[4 * i + 2], w[4 * i + 3], w[4 * i]);
        }

        MainWindow.getArea().append("SUBKEY99: " + subKeys[24][3]);
    }

    private int convertToInt(byte[] key, int i) {
        return ((key[i + 3] & 0xFF) << 24) | ((key[i + 2] & 0xFF) << 16)
                | ((key[i + 1] & 0xFF) << 8) | (key[i] & 0xFF);
    }

    // TODO change
    private void setInitialValue(byte[] iv) {
        if (iv.length > 16)
            throw new Error("bad IV length: " + iv.length);
        if (iv.length == 16) {
            piv = iv;
        } else {
            System.arraycopy(iv, 0, piv, 0, iv.length);
            for (int i = iv.length; i < piv.length; i++)
                piv[i] = 0x00;
        }
    }

    private void serpent24() {
        data[0] = convertToInt(piv, 0);
        data[1] = convertToInt(piv, 4);
        data[2] = convertToInt(piv, 8);
        data[3] = convertToInt(piv, 12);

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

        MainWindow.getArea().append("s9: " + s[9] + "\n");
        MainWindow.getArea().append("s8: " + s[8] + "\n");
        MainWindow.getArea().append("s7: " + s[7] + "\n");
        MainWindow.getArea().append("s6: " + s[6] + "\n");
        MainWindow.getArea().append("s5: " + s[5] + "\n");
        MainWindow.getArea().append("s4: " + s[4] + "\n");
        MainWindow.getArea().append("s3: " + s[3] + "\n");
        MainWindow.getArea().append("s2: " + s[2] + "\n");
        MainWindow.getArea().append("s1: " + s[1] + "\n");
        MainWindow.getArea().append("s0: " + s[0] + "\n");
        MainWindow.getArea().append("R1: " + R1 + "\n");
        MainWindow.getArea().append("R2: " + R2 + "\n");
    }

    private void serpentRound(int index) {

        data[0] = subKeys[index][0] ^ data[0];
        data[1] = subKeys[index][1] ^ data[1];
        data[2] = subKeys[index][2] ^ data[2];
        data[3] = subKeys[index][3] ^ data[3];

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
                output[4 * k + i] = convertToByte(z[i] ^ sOld[i]);
            }
        }
    }

    private byte[] convertToByte(int value) {
        byte[] byteValue = new byte[4];

        byteValue[0] = (byte) value;
        byteValue[1] = (byte) (value >> 8);
        byteValue[2] = (byte) (value >> 16);
        byteValue[3] = (byte) (value >> 24);

        return byteValue;
    }
}
