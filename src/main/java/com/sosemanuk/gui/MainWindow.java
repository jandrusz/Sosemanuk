package com.sosemanuk.gui;

import com.sosemanuk.algorithm.Sosemanuk;
import com.sosemanuk.utils.Stoper;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainWindow {

    private static MainWindow instance = null;

    private static Sosemanuk sosemanuk;

    private static JTextField inputKeyTextField;

    private static JTextField initialValueTextField;

    private static JTextArea area;

    private MainWindow() {
        buildMainFrame();
        sosemanuk = new Sosemanuk();
    }

    public static MainWindow getInstance() {
        return Objects.isNull(instance) ? new MainWindow() : instance;
    }

    private void buildMainFrame() {
        JFrame frame = new JFrame();
        setFrameProperties(frame);
        setContentPaneProperties(frame);
        addComponentToContentPane(frame, getInputKeyTextField());
        addComponentToContentPane(frame, getInitialValueTextField());
        addComponentToContentPane(frame, getLabelForInputKeyTextField());
        addComponentToContentPane(frame, getLabelForInitialVectorTextField());
        addComponentToContentPane(frame, getTextArea());
        addComponentToContentPane(frame, getStartButton());
    }

    private void setFrameProperties(JFrame frame) {
        frame.setTitle("Sosemanuk");
        frame.setBounds(400, 200, 900, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setContentPaneProperties(JFrame frame) {
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().setForeground(Color.LIGHT_GRAY);
        frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getContentPane().setLayout(null);
    }

    private JButton getStartButton() {
        JButton button = new JButton("Start");
        button.setForeground(Color.BLACK);
        button.setBackground(SystemColor.menu);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(20, 500, 380, 50);
        setListenerForButton(button);
        return button;
    }

    private void setListenerForButton(JButton button) {
        button.addActionListener(e -> startAlgorithm());
    }

    private JTextField getInputKeyTextField() {
        inputKeyTextField = new JTextField();
        inputKeyTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputKeyTextField.setHorizontalAlignment(SwingConstants.LEFT);
        inputKeyTextField.setBounds(100, 57, 300, 32);
        return inputKeyTextField;
    }

    private JTextField getInitialValueTextField() {
        initialValueTextField = new JTextField();
        initialValueTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        initialValueTextField.setHorizontalAlignment(SwingConstants.LEFT);
        initialValueTextField.setBounds(100, 120, 300, 32);
        return initialValueTextField;
    }

    private JLabel getLabelForInputKeyTextField() {
        JLabel label = new JLabel("Input key:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(10, 57, 266, 32);
        return label;
    }

    private JLabel getLabelForInitialVectorTextField() {
        JLabel label = new JLabel("Initial value:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(10, 120, 266, 32);
        return label;
    }

    private JTextArea getTextArea() {
        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setBackground(Color.WHITE);
        area.setBounds(425, 10, 450, 540);
        area.setEditable(false);
        area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return area;
    }

    private static JTextArea getArea() {
        return area;
    }

    public static void print(String text) {
        MainWindow.getArea().append(text);
    }

    private void addComponentToContentPane(JFrame frame, JComponent component) {
        frame.getContentPane().add(component);
    }

    private void startAlgorithm() {
        clearTextArea();

        byte[] inputKey;
        byte[] initialValue;

        if (shouldUseUserValues()) {
            inputKey = Sosemanuk.getKey(inputKeyTextField.getText().getBytes());
            initialValue = initialValueTextField.getText().getBytes();
        } else {
            inputKey = Sosemanuk.getKey(getDefaultInputKey());
            initialValue = getDefaultInitialValue();
        }

        Stoper.start();
        sosemanuk.start(inputKey, initialValue);
    }

    private void clearTextArea() {
        area.setText("");
    }

    private boolean shouldUseUserValues() {
        return !Objects.equals(inputKeyTextField.getText(), "") || !Objects.equals(initialValueTextField.getText(), "");
    }

    private byte[] getDefaultInputKey() {
        return new byte[]{
                (byte) 0xA7, (byte) 0xC0, (byte) 0x83, (byte) 0xFE,
                (byte) 0xB7
        };
    }

    private byte[] getDefaultInitialValue() {
        return new byte[]{
                (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33,
                (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77,
                (byte) 0x88, (byte) 0x99, (byte) 0xAA, (byte) 0xBB,
                (byte) 0xCC, (byte) 0xDD, (byte) 0xEE, (byte) 0xFF
        };
    }
}