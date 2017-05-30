package com.sosemanuk;

import com.sosemanuk.gui.MainWindow;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> MainWindow.getInstance());
    }
}
