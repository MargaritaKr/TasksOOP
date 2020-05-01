package ru.academits.kravchenko.temperature_model;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private final List<Scale> scales;

    public Converter() {
        scales = new ArrayList<>();

        scales.add(new Scale() {
            @Override
            public String getName() {
                return "\u00b0C (Celsius)";
            }

            @Override
            public double convertToCelsius(double temperature) {
                return temperature;
            }

            @Override
            public double convertFromCelsius(double temperature) {
                return temperature;
            }
        });

        scales.add(new Scale() {
            @Override
            public String getName() {
                return "\u00b0F (Fahrenheit)";
            }

            @Override
            public double convertToCelsius(double temperature) {
                return (temperature - 32) * 5 / 9;
            }

            @Override
            public double convertFromCelsius(double temperature) {
                return (temperature * 9 / 5 + 32);
            }
        });

        scales.add(new Scale() {
            @Override
            public String getName() {
                return "K (Kelvin)";
            }

            @Override
            public double convertToCelsius(double temperature) {
                return temperature - 273.15;
            }

            @Override
            public double convertFromCelsius(double temperature) {
                return temperature + 273.15;
            }
        });

        /*
        scales.add(new Scale() {
            @Override
            public String getName() {
                return "\u00b0Re (Reaumur)";
            }

            @Override
            public double convertToCelsius(double temperature) {
                return temperature * 1.25;
            }

            @Override
            public double convertFromCelsius(double temperature) {
                return temperature * 0.8;
            }
        });
         */
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