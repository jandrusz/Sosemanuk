package com.sosemanuk;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class MainWindow {

    private static MainWindow instance = null;

    private static Sosemanuk sosemanuk;

    private static JFrame frame;

    private static JTextField inputKeyTextField;

    private static JTextField initialVectorTextField;

    private static JTextArea area;

    private MainWindow() {
        buildMainFrame();
        sosemanuk = new Sosemanuk();
    }

    static MainWindow getInstance() {
        return Objects.isNull(instance) ? new MainWindow() : instance;
    }

    private void buildMainFrame() {
        frame = new JFrame();
        setFrameProperties(frame);
        setContentPaneProperties(frame);
        addComponentToContentPane(frame, getInputKeyTextField());
        addComponentToContentPane(frame, getInitialVectorTextField());
        addComponentToContentPane(frame, getLabelForInputKeyTextField());
        addComponentToContentPane(frame, getLabelForInitialVectorTextField());
        addComponentToContentPane(frame, getTextArea());
        addComponentToContentPane(frame, getStartButton());
    }

    private void setFrameProperties(JFrame frame) {
        frame.setTitle("Sosemanuk");
        frame.setBounds(400, 200, 800, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setContentPaneProperties(JFrame frame) {
        frame.getContentPane().setBackground(SystemColor.LIGHT_GRAY);
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

    private JTextField getInputKeyTextField() {
        inputKeyTextField = new JTextField("§À\u0083þ·");
        inputKeyTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputKeyTextField.setHorizontalAlignment(SwingConstants.LEFT);
        inputKeyTextField.setBounds(150, 57, 200, 32);
        return inputKeyTextField;
    }

    private JTextField getInitialVectorTextField() {
        initialVectorTextField = new JTextField(" \u0011\"3DUfwª»ÌÝîÿ");
        initialVectorTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        initialVectorTextField.setHorizontalAlignment(SwingConstants.LEFT);
        initialVectorTextField.setBounds(150, 120, 200, 32);
        return initialVectorTextField;
    }

    private JLabel getLabelForInputKeyTextField() {
        JLabel label = new JLabel("INPUT KEY:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(10, 57, 266, 32);
        return label;
    }

    private JLabel getLabelForInitialVectorTextField() {
        JLabel label = new JLabel("INITIAL VECTOR:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(10, 120, 266, 32);
        return label;
    }

    private JTextArea getTextArea() {
        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setBackground(Color.WHITE);
        area.setBounds(400, 10, 350, 290);
        area.setEditable(false);
        area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return area;
    }

    private void setListenerForButton(JButton button) {
        byte[] inputKey = {
                (byte) 0xA7, (byte) 0xC0, (byte) 0x83, (byte) 0xFE,
                (byte) 0xB7
        };

        byte[] iv = {
                (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33,
                (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77,
                (byte) 0x88, (byte) 0x99, (byte) 0xAA, (byte) 0xBB,
                (byte) 0xCC, (byte) 0xDD, (byte) 0xEE, (byte) 0xFF
        };

        button.addActionListener(e -> {
            area.setText("");
            sosemanuk.start(getKey(inputKey), iv); //TODO convert input from user to byte in hex
        });
    }

    private byte[] getKey(byte[] inputKey) {
        if (inputKey.length < 0 || inputKey.length > 32) {
            MainWindow.getArea().append("Key should be longer/shorter");
        } else if (inputKey.length == 32) {
            return inputKey;
        }
        return sosemanuk.expandKey(inputKey);
    }

    private void addComponentToContentPane(JFrame frame, JComponent component) {
        frame.getContentPane().add(component);
    }

    static JTextArea getArea() {
        return area;
    }
}
