package ru.academits.kravchenko.temperature_main;

import ru.academits.kravchenko.temperature_model.Converter;
import ru.academits.kravchenko.temperature_view.Window;

public class Controller {
    private static final Converter converter = new Converter();
    private final Window window = new Window(converter.getScalesNames());

    public static double getResult(double temperature, int indexFrom, int indexTo) {
        return converter.getConversion(temperature, indexFrom, indexTo);
    }

    void go() {
        window.show();
    }
}