package com.sosemanuk;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class MainWindow {

    private static MainWindow instance = null;

    private MainWindow() {
        getMainFrame();
    }

    static MainWindow getInstance() {
        return Objects.isNull(instance) ? new MainWindow() : instance;
    }

    private void getMainFrame() {
        JFrame frame = new JFrame();
        setFrame(frame);
        setContentPane(frame);
    }

    private void setFrame(JFrame frame) {
        frame.setTitle("Sosemanuk");
        frame.setBounds(400, 200, 400, 350);
        frame.setVisible(true);
    }

    private void setContentPane(JFrame frame) {
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.getContentPane().setForeground(Color.LIGHT_GRAY);
        frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getContentPane().setLayout(null);
    }

    //TODO inne rzeczy do wpisywania, wypisywania i klikania - do ustalenia

}
