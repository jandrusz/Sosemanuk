package com.sosemanuk.utils;

public class Stoper {

    private static long timeStart;
    private static long timeStop;

    public static void start() {
        timeStart = System.currentTimeMillis();
    }

    public static void stop() {
        timeStop = System.currentTimeMillis();
    }

    public static long getTime() {
        return timeStop - timeStart;
    }
}
