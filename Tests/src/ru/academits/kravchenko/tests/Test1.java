package ru.academits.kravchenko.tests;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        Switcher sw = new Switcher();

        Lamp lamp = new Lamp();
        Radio radio = new Radio();

        sw.addElectricityListener(lamp);
        sw.addElectricityListener(radio);

        sw.addElectricityListener(() -> System.out.println("Пожар!"));

        sw.switchOn();

        List<Integer> list = new ArrayList<>();

        list.add(5);
        list.add(4);
        list.add(6);

        System.out.println(list.get(5));

    }
}
