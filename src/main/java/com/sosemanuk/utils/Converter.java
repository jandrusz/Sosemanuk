package com.sosemanuk.utils;

public class Converter {

    public static byte[] convertToByte(int value) {
        byte[] byteValue = new byte[4];
        byteValue[0] = (byte) value;
        byteValue[1] = (byte) (value >> 8);
        byteValue[2] = (byte) (value >> 16);
        byteValue[3] = (byte) (value >> 24);
        return byteValue;
    }

    public static int convertToInt(byte[] key, int i) {
        return ((key[i + 3] & 0xFF) << 24) | ((key[i + 2] & 0xFF) << 16)
                | ((key[i + 1] & 0xFF) << 8) | (key[i] & 0xFF);
    }

    public static byte[] convertTwoDimToOneDim(byte[][] twoDimArray) {
        byte[] oneDimArray = new byte[160];
        int count = 0;
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 4; j++) {
                oneDimArray[count] = twoDimArray[i][j];
                count++;
            }
        return oneDimArray;
    }

}
