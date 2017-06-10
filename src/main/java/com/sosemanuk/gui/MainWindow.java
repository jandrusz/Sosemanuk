package com.sosemanuk.gui;

import com.sosemanuk.algorithm.Sosemanuk;
import com.sosemanuk.utils.Converter;
import com.sosemanuk.utils.PrintUtil;
import com.sosemanuk.utils.Stopwatch;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Klasa odpowiedzialna za stworzenie interfejsu użykownika.
 */
public class MainWindow {

    private Sosemanuk sosemanuk;

    private JTextField inputKeyTextField;

    private JTextField initialValueTextField;

    private JFrame frame;

    private FileDialog fileDialog;

    private JLabel label;

    private Path path;

    private static JTextArea area;

    private static byte[] originalFile;

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
        frame = new JFrame();
        setFrameProperties(frame);
        frame.add(getInputKeyTextField());
        frame.add(getInitialValueTextField());
        frame.add(getLabelForInputKeyTextField());
        frame.add(getLabelForInitialVectorTextField());
        frame.add(getTextArea());
        frame.add(getScroll());
        frame.add(getEncodeDecodeButton());
        frame.add(getImportButton());
        frame.add(getSaveButton());
        frame.add(getPathLabel());
    }

    /**
     * Metoda ustawiająca właściwości ramki interfejsu.
     *
     * @param frame obiekt JFrame któremu chcemy nadać odpowiednie własćiwości
     */
    private void setFrameProperties(JFrame frame) {
        frame.setTitle("Sosemanuk stream cipher");
        frame.setBounds(400, 200, 850, 480);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości przycisku "Zaszyfruj/Odszyfruj".
     *
     * @return odpowiednio sformatowany obiekt klasy JButton
     */
    private JButton getEncodeDecodeButton() {
        JButton button = new JButton("Encode/Decode");
        button.setForeground(Color.BLACK);
        button.setBackground(SystemColor.menu);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(20, 320, 380, 50);
        setListenerForEncodeDecodeButton(button);
        return button;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości przycisku "Wybierz plik".
     *
     * @return odpowiednio sformatowany obiekt klasy JButton
     */
    private JButton getImportButton() {
        JButton button = new JButton("Choose file");
        button.setForeground(Color.BLACK);
        button.setBackground(SystemColor.menu);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(20, 260, 380, 50);
        setListenerForImportButton(button);
        return button;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości labelki "Wybrany plik".
     *
     * @return odpowiednio sformatowany obiekt klasy JLabel
     */
    private JLabel getPathLabel() {
        label = new JLabel();
        label.setBounds(20, 220, 380, 50);
        label.setText("Chosen file:");
        return label;
    }

    /**
     * Metoda odpowiedzialna za ustawienie właściwości przycisku "Zapisz plik na dysku".
     *
     * @return odpowiednio sformatowany obiekt klasy JButton
     */
    private JButton getSaveButton() {
        JButton button = new JButton("Save file");
        button.setForeground(Color.BLACK);
        button.setBackground(SystemColor.menu);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBounds(20, 380, 380, 50);
        setListenerForSaveButton(button);
        return button;
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
        inputKeyTextField.setBounds(100, 60, 300, 32);
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
        label.setBounds(20, 60, 266, 30);
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
        label.setBounds(20, 120, 266, 30);
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
        area.setBounds(425, 10, 400, 420);
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
        scroll.setBounds(425, 10, 400, 420);
        return scroll;
    }

    /**
     * Metoda ustawiająca nasłuichiwnie na wydarzenie po wciśnięciu przycisku Zaszyfruj/Odszyfruj
     *
     * @param button obiekt klasy JButton któremu przypisujemy wykonanie metody po naciśnięciu przycisku
     */
    private void setListenerForEncodeDecodeButton(JButton button) {
        button.addActionListener(e -> {
            if (isFilePresent()) {
                startAlgorithm();
            } else {
                clearTextArea();
                PrintUtil.print("First you have to choose file\n");
            }
        });
    }

    /**
     * Metoda ustawiająca nasłuichiwnie na wydarzenie po wciśnięciu przycisku Wybierz plik
     *
     * @param button obiekt klasy JButton któremu przypisujemy wykonanie metody po naciśnięciu przycisku
     */
    private void setListenerForImportButton(JButton button) {
        button.addActionListener(e -> {
            try {
                importAndConvertFile();
                label.setText("Chosen file: " + path.getParent() + "\\" + path.getFileName());
                label.setToolTipText(label.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Metoda ustawiająca nasłuichiwnie na wydarzenie po wciśnięciu przycisku Zapisz plik
     *
     * @param button obiekt klasy JButton któremu przypisujemy wykonanie metody po naciśnięciu przycisku
     */
    private void setListenerForSaveButton(JButton button) {
        button.addActionListener(e -> {
            if (isFilePresent()) {
                saveFile();
            } else {
                clearTextArea();
                PrintUtil.print("First you have to choose file\n");
            }
        });
    }

    /**
     * Metoda służąca do zapisu zaszyfrowanego pliku
     */
    private void saveFile() {
        fileDialog = new FileDialog(frame, "Save file", FileDialog.SAVE);
        fileDialog.setVisible(true);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileDialog.getDirectory() + fileDialog.getFile());
            fileOutputStream.write(sosemanuk.getEncodedFile());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda służąca do pobrania od użytkownika pliku do zaszyfrowania
     *
     * @throws Exception
     */
    private void importAndConvertFile() throws IOException {
        fileDialog = new FileDialog(frame, "Choose file", FileDialog.LOAD);
        fileDialog.setVisible(true);
        if (Objects.nonNull(fileDialog.getFile())) {
            path = Paths.get(fileDialog.getDirectory() + fileDialog.getFile());
            originalFile = Converter.convertFileToBytes(path);
        }
    }

    /**
     * Funkcja typu getter
     *
     * @return originalFile
     */
    public static byte[] getOriginalFile() {
        return originalFile;
    }

    /**
     * Metoda sprawdza czy został wybrany plik do szyfrowania
     *
     * @return true/false
     */
    private boolean isFilePresent() {
        return Objects.nonNull(originalFile);
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

        Stopwatch.reset();
        if (shouldUseUserValues()) {
            PrintUtil.print("User values were used\n");
            Stopwatch.start();
            inputKey = Sosemanuk.prepareKey(inputKeyTextField.getText().getBytes());
            initialValue = Sosemanuk.prepareInitialValue(initialValueTextField.getText().getBytes());
        } else {
            PrintUtil.print("Default values were used\n");
            Stopwatch.start();
            inputKey = Sosemanuk.prepareKey(getDefaultInputKey());
            initialValue = Sosemanuk.prepareInitialValue(getDefaultInitialValue());
        }

        Stopwatch.stop();
        PrintUtil.showInputData(inputKey, initialValue);
        Stopwatch.start();
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
