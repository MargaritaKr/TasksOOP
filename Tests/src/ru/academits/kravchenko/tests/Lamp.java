package ru.academits.kravchenko.tests;

public class Lamp implements ElectricityConsumer {
    private void lightOn(){
        System.out.println("Лампа зажглась");
    }

    @Override
    public void electricityOn() {
        lightOn();

    }
}
