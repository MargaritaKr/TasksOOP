package ru.academits.kravchenko.temperature_model;

public class FahrenheitScale implements Scale{
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
}
