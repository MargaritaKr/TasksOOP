package ru.academits.kravchenko.temperature_model;

public interface Scale {
    String getName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
