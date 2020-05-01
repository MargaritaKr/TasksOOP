package ru.academits.kravchenko.temperature_model;

public class ReaumurScale implements Scale{
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
}
