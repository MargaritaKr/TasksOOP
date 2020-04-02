package ru.academits.kravchenko.tests;

import java.util.ArrayList;
import java.util.List;

class Switcher {
    private List<ElectricityConsumer> listeners = new ArrayList<>();

    void addElectricityListener(ElectricityConsumer listener) {
        listeners.add(listener);
    }

    void switchOn() {
        System.out.println("Выключатель включен");

        for (ElectricityConsumer c : listeners) {
            c.electricityOn();
        }
    }
}
