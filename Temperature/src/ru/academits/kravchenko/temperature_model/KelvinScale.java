package ru.academits.kravchenko.temperature_model;

public class KelvinScale implements Scale{
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
}
