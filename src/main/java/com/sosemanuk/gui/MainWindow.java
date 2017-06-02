package com.sosemanuk.gui;

import com.sosemanuk.algorithm.Sosemanuk;
import com.sosemanuk.utils.PrintUtil;
import com.sosemanuk.utils.Stoper;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Klasa odpowiedzialna za stworzenie interfejsu użykownika.
 */
public class MainWindow {

    private Sosemanuk sosemanuk;

    private JTextField inputKeyTextField;

    private JTextField initialValueTextField;

    private static JTextArea area;

    /**
     * Konstruktor klasy.
     */
    public MainWindow() {
        buildMainFrame();
        sosemanuk = new Sosemanuk();
    }

    /**
     * Metoda budująca interfejs użytkownika.
     */
    private void buildMainFrame() {
        JFrame frame = new JFrame();
        setFrameProperties(frame);
        frame.add(getInputKeyTextField());
        frame.add(getInitialValueTextField());
        frame.add(getLabelForInputKeyTextField());
        frame.add(getLabelForInitialVectorTextField());
        frame.add(getTextArea());
        frame.add(getScroll());
        frame.add(getStartButton());
    }

    /**
     * Metoda ustawiająca właściwości ramki interfejsu.
     *
     * @param frame obiekt JFrame któremu chcemy nadać odpowiednie własćiwości
     */
    private void setFrameProperties(JFrame frame) {
        frame.setTitle("Sosemanuk");
        frame.setBounds(400, 200, 900, 600);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości przycisku "start".
     *
     * @return odpowiednio sformatowany obiekt klasy JButton
     */
    private JButton getStartButton() {
        JButton button = new JButton("Start");
        button.setForeground(Color.BLACK);
        button.setBackground(SystemColor.menu);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(20, 500, 380, 50);
        setListenerForButton(button);
        return button;
    }

    /**
     * Metoda ustawiająca nasłuichiwnie na wydarzenie po wciśnięciu przycisku start
     *
     * @param button obiekt klasy JButton któremu przypisujemy wykonanie metody po naciśnięciu przycisku
     */
    private void setListenerForButton(JButton button) {
        button.addActionListener(e -> startAlgorithm());
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości pola tekstowego
     *
     * @return odpowiednio sformatowane pole inputKeyTextField
     */
    private JTextField getInputKeyTextField() {
        inputKeyTextField = new JTextField();
        inputKeyTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputKeyTextField.setHorizontalAlignment(SwingConstants.LEFT);
        inputKeyTextField.setBounds(100, 57, 300, 32);
        return inputKeyTextField;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości pola tekstowego
     *
     * @return odpowiednio sformatowane pole initialValueTextField
     */
    private JTextField getInitialValueTextField() {
        initialValueTextField = new JTextField();
        initialValueTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        initialValueTextField.setHorizontalAlignment(SwingConstants.LEFT);
        initialValueTextField.setBounds(100, 120, 300, 32);
        return initialValueTextField;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości belki z tekstem.
     *
     * @return odpowiednio sformatowany obiekt klasy JLabel
     */
    private JLabel getLabelForInputKeyTextField() {
        JLabel label = new JLabel("Input key:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(10, 57, 266, 32);
        return label;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości belki z tekstem.
     *
     * @return odpowiednio sformatowany obiekt klasy JLabel
     */
    private JLabel getLabelForInitialVectorTextField() {
        JLabel label = new JLabel("Initial value:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(10, 120, 266, 32);
        return label;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości pola tekstowego
     *
     * @return odpowiednio sformatowane pole area
     */
    private JTextArea getTextArea() {
        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setBackground(Color.WHITE);
        area.setBounds(425, 10, 450, 540);
        area.setEditable(false);
        area.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return area;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości paska przwijania.
     *
     * @return odpowiednio sformatowany obiekt klasy JScrollPane
     */
    private JScrollPane getScroll() {
        JScrollPane scroll = new JScrollPane(area,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(425, 10, 450, 540);
        return scroll;
    }

    /**
     * @return pole area
     */
    public static JTextArea getArea() {
        return area;
    }

    /**
     * Metoda rozpoczynająca pracę algorytmu.
     */
    private void startAlgorithm() {
        clearTextArea();
        byte[] inputKey;
        byte[] initialValue;

        Stoper.reset();
        if (shouldUseUserValues()) {
            PrintUtil.print("Wykorzystano wprowadzone dane \n");
            Stoper.start();
            inputKey = Sosemanuk.prepareKey(inputKeyTextField.getText().getBytes());
            initialValue = Sosemanuk.prepareInitialValue(initialValueTextField.getText().getBytes());
        } else {
            PrintUtil.print("Wykorzystano dane domyślne \n");
            Stoper.start();
            inputKey = Sosemanuk.prepareKey(getDefaultInputKey());
            initialValue = Sosemanuk.prepareInitialValue(getDefaultInitialValue());
        }

        Stoper.stop();
        PrintUtil.showInputData(inputKey, initialValue);
        Stoper.start();
        sosemanuk.start(inputKey, initialValue);
    }

    /**
     * Metoda czyszcząca pole tekstowe GUI.
     */
    private void clearTextArea() {
        area.setText("");
    }

    /**
     * Metoda weryfikująca czy użytkownik wprowadził wartości początkowe i czy w związku z tym należy ich użyć.
     *
     * @return wartość logiczna określająca czy algorytm ma działać na danych wprowadzonych przez użytkownika
     */
    private boolean shouldUseUserValues() {
        return !Objects.equals(inputKeyTextField.getText(), "") || !Objects.equals(initialValueTextField.getText(), "");
    }

    /**
     * Metoda pobierająca domyślny klucz w razie gdyby użykownik nie wypełnił przeznaozonych do tego pól.
     *
     * @return domyślny klucz
     */
    private byte[] getDefaultInputKey() {
        return new byte[]{
                (byte) 0xA7, (byte) 0xC0, (byte) 0x83, (byte) 0xFE,
                (byte) 0xB7
        };
    }

    /**
     * Metoda pobierająca domyślną wartość inicjalną w razie gdyby użykownik nie wypełnił przeznaozonych do tego pól.
     *
     * @return domyślna wartość inicjalna
     */
    private byte[] getDefaultInitialValue() {
        return new byte[]{
                (byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0x33,
                (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77,
                (byte) 0x88, (byte) 0x99, (byte) 0xAA, (byte) 0xBB,
                (byte) 0xCC, (byte) 0xDD, (byte) 0xEE, (byte) 0xFF
        };
    }
}
