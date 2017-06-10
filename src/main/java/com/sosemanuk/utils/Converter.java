package com.sosemanuk.utils;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Klasa odpowiedzialna za wszelkie konwersje danych.
 */
public class Converter {
    /**
     * Metoda konwertująca int na tablicę bitów.
     *
     * @param value wartość int do konwersji
     * @return Tablica bitów.
     */
    public static byte[] convertToByte(int value) {
        byte[] byteValue = new byte[4];
        byteValue[0] = (byte) value;
        byteValue[1] = (byte) (value >> 8);
        byteValue[2] = (byte) (value >> 16);
        byteValue[3] = (byte) (value >> 24);
        return byteValue;
    }

    /**
     * Metoda konwertująca tablicę bitów na wartość typu int.
     *
     * @param array tablica do przekonwertowania
     * @param i     przesunięcie
     * @return wartość tablicy bitów w formacie int
     */
    public static int convertToInt(byte[] array, int i) {
        return ((array[i + 3] & 0xFF) << 24) | ((array[i + 2] & 0xFF) << 16)
                | ((array[i + 1] & 0xFF) << 8) | (array[i] & 0xFF);
    }

    /**
     * Funkcja zamieniająca dwuwymiarową tablicę bitów do tablicy jednowymiarowej.
     *
     * @param twoDimensionalArray dwuwymiarowa tablica bitów
     * @return tablica bitów
     */
    public static byte[] convertTwoDimensionalToOneDimensional(byte[][] twoDimensionalArray) {
        byte[] oneDimArray = new byte[160];
        int count = 0;
        for (int i = 0; i < 40; i++)
            for (int j = 0; j < 4; j++) {
                oneDimArray[count] = twoDimensionalArray[i][j];
                count++;
            }
        return oneDimArray;
    }

    /**
     * Metoda służąca do przekonwertowania pliku na tablice bitów
     *
     * @param path ścieżka do pliku
     * @return tablica bitów
     * @throws Exception
     */
    public static byte[] convertFileToBytes(Path path) throws Exception {
        try {
            return Files.readAllBytes(path);
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
