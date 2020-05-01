package ru.academits.kravchenko.temperature_model;

public class CelsiusScale implements Scale{
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
}
