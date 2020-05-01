package ru.academits.kravchenko.temperature_view;

import ru.academits.kravchenko.temperature_main.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Window {
    private static final Color backgroundColor = new Color(200, 230, 180);
    private static final Font infoFont = new Font("Arial", Font.ITALIC, 12);

    private String[] scales;

    private JComboBox<String> scalesInput;
    private JTextField temperatureInput;
    private JRadioButton[] radioButtons;
    private JLabel result;

    private final ActionListener formatListener = e -> {
        int scale = scalesInput.getSelectedIndex();

        for (int i = 0; i < scales.length; i++) {
            radioButtons[i].setEnabled(true);
        }

        radioButtons[scale].setEnabled(false);
        radioButtons[0].setSelected(scale != 0);
        radioButtons[1].setSelected(scale == 0);
    };

    private final ActionListener mainListener = e -> {
        try {
            String inputText = temperatureInput.getText().replace(",", ".");

            double temperatureFrom = Double.parseDouble(inputText);
            int scaleFromIndex = scalesInput.getSelectedIndex();

            int scaleToIndex = 0;

            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    scaleToIndex = i;
                    break;
                }
            }

            double temperatureTo = Controller.getResult(temperatureFrom, scaleFromIndex, scaleToIndex);

            String resultStr = String.format("%.2f %s = %.2f %s", temperatureFrom, scales[scaleFromIndex],
                    temperatureTo, scales[scaleToIndex]);

            result.setText(resultStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "The entered value is not correct!" +
                    System.lineSeparator() + "Enter a number.");
        }
    };

    public Window(String[] scales) {
        this.scales = scales;
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature Converter");

            frame.setSize(800, 400);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //создаем контейнер с основным контентом
            JPanel mainContainer = new JPanel();
            mainContainer.setBackground(backgroundColor);

            JLabel info1 = new JLabel("Enter the conversion value:");
            info1.setFont(infoFont);

            JLabel info2 = new JLabel("Select the translation scale:");
            info2.setFont(infoFont);

            GridBagLayout gbl = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            mainContainer.setLayout(gbl);

            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.BOTH;
            c.gridheight = 1;
            c.gridwidth = 0;
            c.insets = new Insets(20, 5, 10, 5);

            mainContainer.add(info1, c);
            mainContainer.add(createInputPanel(), c);
            mainContainer.add(info2, c);
            mainContainer.add(createRadioButtonsPanel(), c);
            mainContainer.add(createButtonResultPanel(), c);
            mainContainer.add(createResultPanel(), c);

            //помещаем все в общую форму
            JPanel contents = new JPanel(new BorderLayout());

            contents.add(new JLabel(new ImageIcon("Temperature/blue.png")), BorderLayout.LINE_START);
            contents.add(new JLabel(new ImageIcon("Temperature/red.png")), BorderLayout.LINE_END);
            contents.add(mainContainer, BorderLayout.CENTER);

            Image icon = Toolkit.getDefaultToolkit().getImage("Temperature/icon.jpg");
            frame.setIconImage(icon);

            frame.setContentPane(contents);
            frame.setVisible(true);
        });
    }

    //создаем контейнер с полями ввода
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        inputPanel.setBackground(backgroundColor);

        temperatureInput = new JTextField();
        temperatureInput.setHorizontalAlignment(JTextField.CENTER);

        scalesInput = new JComboBox<>(scales);
        scalesInput.addActionListener(formatListener);

        inputPanel.add(temperatureInput);
        inputPanel.add(scalesInput);

        return inputPanel;
    }

    //создаем панель для выбора параметров результата
    private JPanel createRadioButtonsPanel() {
        int rows = (scales.length % 3 != 0) ? scales.length / 3 + 1 : scales.length / 3;

        JPanel radioButtonsPanel = new JPanel(new GridLayout(rows, 3, 0, 0));
        radioButtonsPanel.setBackground(backgroundColor);

        ButtonGroup radioButtonsGroup = new ButtonGroup();

        radioButtons = new JRadioButton[scales.length];

        for (int i = 0; i < scales.length; i++) {
            radioButtons[i] = new JRadioButton(scales[i], (i == 1));
            radioButtonsGroup.add(radioButtons[i]);
            radioButtonsPanel.add(radioButtons[i]);

            radioButtons[i].setEnabled(i != 0);
        }

        return radioButtonsPanel;
    }

    //создаем контейнер с главной кнопкой
    private JPanel createButtonResultPanel() {
        JPanel buttonResultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonResultPanel.setBackground(backgroundColor);

        JButton getResult = new JButton("Convert");
        buttonResultPanel.add(getResult);

        getResult.addActionListener(mainListener);

        return buttonResultPanel;
    }

    //создаем контейнер с результатом
    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setBackground(backgroundColor);

        result = new JLabel();
        result.setText("");
        resultPanel.add(result);

        return resultPanel;
    }
}