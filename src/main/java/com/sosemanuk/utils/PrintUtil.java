package com.sosemanuk.utils;

import com.sosemanuk.gui.MainWindow;

/**
 * Klasa odpowiedzialna za formatowanie tekstu i wyświetlenie go użytkownikowi.
 */
public class PrintUtil {

    private static char[] hexnum = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * Funkcja pobiera dwuwymiarową tablicę bitów i drukuje wynik w formacie HEX w GUI.
     *
     * @param byteArray dwuwymiarowa tablica bitów
     */
    public static void printResult(byte[][] byteArray) {
        byte[] array = Converter.convertTwoDimensionalToOneDimensional(byteArray);

        print("Result: \n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                int v = array[i * 16 + j] & 0xFF;
                print((" " + hexnum[v >> 4])
                        + hexnum[v & 0x0F]);
            }
            print("\n");
        }
    }

    /**
     * Metoda do wyświetlania tekstu na GUI.
     *
     * @param text tekst który ma zostać wyświetlony
     */
    public static void print(String text) {
        MainWindow.getArea().append(text);
    }

}
