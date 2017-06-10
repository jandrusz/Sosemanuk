package com.sosemanuk.utils;

import com.sosemanuk.gui.MainWindow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Klasa odpowiedzialna za formatowanie tekstu i wyświetlenie go użytkownikowi.
 */
public class PrintUtil {

    private static char[] hexnum = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /**
     * Funkcja służąca do wyświetlania klucza
     *
     * @param byteArray tablica bitów
     */
    private static void printInputKey(byte[] byteArray) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                int v = byteArray[i * 4 + j] & 0xFF;
                print((" " + hexnum[v >> 4])
                        + hexnum[v & 0x0F]);
            }
            print("\n");
        }
    }

    /**
     * Funkcja służąca do wyświetlania wartości inicjalnej
     *
     * @param byteArray tablica bitów
     */
    private static void printInitialValue(byte[] byteArray) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int v = byteArray[i * 4 + j] & 0xFF;
                print((" " + hexnum[v >> 4])
                        + hexnum[v & 0x0F]);
            }
            print("\n");
        }
    }

    /**
     * Funkca służacą do wyświetlania danych początkowych
     *
     * @param inputKey     klucz
     * @param initialValue wartość inicjalna
     */
    public static void showInputData(byte[] inputKey, byte[] initialValue) {
        PrintUtil.print("Input key: \n");
        printInputKey(inputKey);
        PrintUtil.print("Initial value: \n");
        printInitialValue(initialValue);
    }

    /**
     * Funkcja wyświetlająca wynik działania szyfru.
     */
    public static void getResult() {
        print("\nFINISHED - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
        String czas;
        if (Stopwatch.getTime() == 0)
            czas = "<1 ms";
        else {
            czas = Stopwatch.getTime() + " ms";
        }
        print("Time: " + czas + "\n");
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
