package ru.academits.kravchenko.range_main;

import ru.academits.kravchenko.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double criticalValue = 17.9;

        System.out.println("Введите параметры диапазона по результатам испытаний:");

        System.out.print("значение нижней границы - ");
        double lowerBound = scanner.nextDouble();

        System.out.print("значение верхней границы - ");
        double upperBound = scanner.nextDouble();

        Range factualDiapason = new Range(lowerBound, upperBound);

        double diapasonLength = factualDiapason.getLength();

        System.out.printf("Длина диапазона составляет: %.4f  ед.изм.%n", diapasonLength);

        if (factualDiapason.isInside(criticalValue)) {
            System.out.println("По результатам испытаний диапазон содержит критическое значение.");
        } else {
            System.out.println("По результатам испытаний диапазон не содержит критическое значение.");
        }
    }
}

