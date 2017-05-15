package com.sosemanuk;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class MainWindow {

    private static MainWindow instance = null;

    private Algorithm algorithm;

    private MainWindow() {
        getMainFrame();
        algorithm = new AlgorithmImpl();
    }

    static MainWindow getInstance() {
        return Objects.isNull(instance) ? new MainWindow() : instance;
    }

    private void getMainFrame() {
        JFrame frame = new JFrame();
        setFrameProperties(frame);
        setContentPaneProperties(frame);
        addComponentToContentPane(frame, getStartButton());
    }

    private void setFrameProperties(JFrame frame) {
        frame.setTitle("Sosemanuk");
        frame.setBounds(400, 200, 400, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setContentPaneProperties(JFrame frame) {
        frame.getContentPane().setBackground(SystemColor.GRAY);
        frame.getContentPane().setForeground(Color.LIGHT_GRAY);
        frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getContentPane().setLayout(null);
    }

    private JButton getStartButton() {
        JButton button = new JButton("Start");
        button.setForeground(Color.BLACK);
        button.setBackground(SystemColor.menu);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(20, 250, 350, 50);
        setListenerForButton(button);
        return button;
    }

    private void setListenerForButton(JButton button) {
        button.addActionListener(e -> algorithm.start());
    }

    private void addComponentToContentPane(JFrame frame, JComponent component) {
        frame.getContentPane().add(component);
    }
}
