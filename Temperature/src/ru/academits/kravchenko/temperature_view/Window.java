package ru.academits.kravchenko.temperature_view;

import ru.academits.kravchenko.temperature_model.Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Window {
    private static Color backgroundColor = new Color(200, 230, 180);
    private static Font infoFont = new Font("Arial", Font.ITALIC, 12);
    private static String[] scales = new String[]{"\u2103 (Celsius)", "\u2109 (Fahrenheit)", "K (Kelvin)"};

    private JComboBox scalesInput;
    private JTextField temperatureInput;
    private JRadioButton celsiusRB;
    private JRadioButton fahrenheitRB;
    private JRadioButton kelvinRB;
    private JLabel result;

    public void show() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Перевод температур");
            frame.setSize(800, 400);
            frame.setResizable(false);                                               // запрещаем растягиваться
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //создаем контейнер с основным контентом
            JPanel mainContainer = new JPanel();
            mainContainer.setBackground(backgroundColor);

            JLabel info1 = new JLabel("Введите значение для конвертации:");
            info1.setFont(infoFont);

            JLabel info2 = new JLabel("Выберете шкалу перевода:");
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

            //помещаем все в общую фоорму
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
        JPanel inputPanel = new JPanel(new GridLayout());
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
        JPanel radioButtonsPanel = new JPanel(new GridLayout(1, 3, 0, 0));
        radioButtonsPanel.setBackground(backgroundColor);

        celsiusRB = new JRadioButton(scales[0]);
        fahrenheitRB = new JRadioButton(scales[1], true);
        kelvinRB = new JRadioButton(scales[2]);
        celsiusRB.setEnabled(false);

        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(celsiusRB);
        radioButtons.add(fahrenheitRB);
        radioButtons.add(kelvinRB);

        radioButtonsPanel.add(celsiusRB);
        radioButtonsPanel.add(fahrenheitRB);
        radioButtonsPanel.add(kelvinRB);

        return radioButtonsPanel;
    }

    //создаем контейнер с главной кнопкой
    private JPanel createButtonResultPanel() {
        JPanel buttonResultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonResultPanel.setBackground(backgroundColor);

        JButton getResult = new JButton("Конвертировать");
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

    private ActionListener formatListener = e -> {
        int scale = scalesInput.getSelectedIndex();

        celsiusRB.setEnabled(true);
        fahrenheitRB.setEnabled(true);
        kelvinRB.setEnabled(true);

        switch (scale) {
            case 0:
                celsiusRB.setEnabled(false);
                fahrenheitRB.setSelected(true);
                break;
            case 1:
                fahrenheitRB.setEnabled(false);
                celsiusRB.setSelected(true);
                break;
            case 2:
                kelvinRB.setEnabled(false);
                celsiusRB.setSelected(true);
                break;
        }
    };

    private ActionListener mainListener = e -> {
        try {
            String inputText = temperatureInput.getText().replace(",", ".");

            double input = Double.parseDouble(inputText);
            int scaleFrom = scalesInput.getSelectedIndex();
            int scaleTo = 0;

            if (fahrenheitRB.isSelected()) {
                scaleTo = 1;
            }

            if (kelvinRB.isSelected()) {
                scaleTo = 2;
            }

            String resultStr = String.format("%.2f %s = %.2f %s", input, scales[scaleFrom],
                    Converter.getResult(input, scaleFrom, scaleTo), scales[scaleTo]);

            result.setText(resultStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Введенное значение не корректно!" +
                    System.lineSeparator() + "Введите число.");
        }
    };
}