package com.sosemanuk;

import java.awt.*;

public class App {
    public static void main(String[] args) {
       // EventQueue.invokeLater(() -> MainWindow.getInstance());

        Sosemanuk sosemanuk =  new Sosemanuk();

        // TODO get key from user
        byte[] inputKey = {
                (byte)0xA7, (byte)0xC0, (byte)0x83, (byte)0xFE,
                (byte)0xB7
        };

        byte[] iv = {
                (byte)0x00, (byte)0x11, (byte)0x22, (byte)0x33,
                (byte)0x44, (byte)0x55, (byte)0x66, (byte)0x77,
                (byte)0x88, (byte)0x99, (byte)0xAA, (byte)0xBB,
                (byte)0xCC, (byte)0xDD, (byte)0xEE, (byte)0xFF
        };



        byte[] key = new byte[32];

        if(inputKey.length < 0 || inputKey.length > 32){
            //TODO print message to user that key is too long/short
            System.out.println("Key should be longer/shorter");
        } else if(inputKey.length == 32){
            key = inputKey;
        } else {
            key = sosemanuk.expandKey(inputKey);
        }



        sosemanuk.keySchedule(key);
        sosemanuk.setInitialValue(iv);
        sosemanuk.serpent24();
        sosemanuk.workflow();




    }
}
