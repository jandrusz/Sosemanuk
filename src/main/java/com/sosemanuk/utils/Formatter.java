package com.sosemanuk.utils;

import com.sosemanuk.MainWindow;

public class Formatter {

    public static void printResult(byte[][] byteArray) {
        byte[] array = Converter.convertTwoDimToOneDim(byteArray);

        char[] hexnum = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };

        MainWindow.print("Result: \n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                int v = array[i * 16 + j] & 0xFF;
                MainWindow.print((" " + hexnum[v >> 4])
                        + hexnum[v & 0x0F]);
            }
            MainWindow.print("\n");
        }
    }
}
