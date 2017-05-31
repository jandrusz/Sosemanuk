package com.sosemanuk.utils;

/**
 * Klasa pomocnicza umożliwiająca obliczenie czas jakiego
 * potrzebuje algorytm.
 */
public class Stoper {

    private static long timeStart;

    private static long totalTime;

    /**
     * Metoda startująca stoper.
     */
    public static void start() {
        timeStart = System.currentTimeMillis();
    }

    /**
     * Metoda zatrzymująca stoper.
     */
    public static void stop() {
        totalTime += System.currentTimeMillis() - timeStart;
    }

    /**
     * Funkcja resetująca stoper.
     */
    public static void reset() {
        timeStart = 0;
        totalTime = 0;
    }

    /**
     * Metoda zwracająca czas w milisekundach który upłynął pomiędzy wywołaniami {@link #start()} i {@link #stop()}.
     *
     * @return czas w milisekundach
     */
    static long getTime() {
        return totalTime;
    }
}
