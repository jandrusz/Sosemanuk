package com.sosemanuk.utils;

/**
 * Klasa pomocnicza umożliwiająca obliczenie czas jakiego
 * potrzebuje algorytm.
 */
public class Stoper {

    private static long timeStart;

    private static long timeStop;

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
        timeStop = System.currentTimeMillis();
    }

    /**
     * Metoda umożliwiająca pobranie czasu jaki minął od momentu wywołania metody {@link #start()}.
     *
     * @return Czas jaki minął od startu stopera.
     */
    public static long getTimeFromStart() {
        return System.currentTimeMillis() - timeStart;
    }

    /**
     * Metoda zwracająca czas w milisekundach który upłynął pomiędzy wywołaniami {@link #start()} i {@link #stop()}.
     *
     * @return czas w milisekundach
     */
    public static long getTime() {
        return timeStop - timeStart;
    }
}
