package ru.academits.kravchenko.range_main;

import ru.academits.kravchenko.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double criticalValue = 17;

        System.out.println("Введите параметры диапазона по результатам испытаний:");

        System.out.print("значение нижней границы: ");
        double from = scanner.nextDouble();

        System.out.print("значение верхней границы: ");
        double to = scanner.nextDouble();

        Range factualRange = new Range(from, to);

        double diapasonLength = factualRange.getLength();

        System.out.printf("Длина диапазона составляет: %.4f  ед.изм.%n", diapasonLength);

        if (factualRange.isInside(criticalValue)) {
            System.out.println("По результатам испытаний диапазон содержит критическое значение.");
        } else {
            System.out.println("По результатам испытаний диапазон не содержит критическое значение.");
        }
    }
}