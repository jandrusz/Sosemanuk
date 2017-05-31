package com.sosemanuk;

import com.sosemanuk.gui.MainWindow;

import java.awt.*;

/**
 * Klasa ropoczynająca pracę z programem
 */
public class App {
    /**
     * Metoda inicjalizująca główne okno programu
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainWindow());
    }
}
