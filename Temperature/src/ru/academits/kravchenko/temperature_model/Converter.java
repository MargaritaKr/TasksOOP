package ru.academits.kravchenko.temperature_model;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private final List<Scale> scales;

    public Converter() {
        scales = new ArrayList<>();

        scales.add(new CelsiusScale());
        scales.add(new FahrenheitScale());
        scales.add(new KelvinScale());
        //scales.add(new ReaumurScale());
    }

    public String[] getScalesNames() {
        String[] names = new String[scales.size()];

        for (int i = 0; i < scales.size(); i++) {
            names[i] = scales.get(i).getName();
        }

        return names;
    }

    public double getConversion(double temperature, int from, int to) {
        return scales.get(to).convertFromCelsius(scales.get(from).convertToCelsius(temperature));
    }
}