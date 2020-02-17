package ru.academits.kravchenko.hard_range_main;

import ru.academits.kravchenko.hard_range.Range;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите параметры первого диапазона");

        System.out.print("значение нижней границы: ");
        double from = scanner.nextDouble();

        System.out.print("значение верхней границы: ");
        double to = scanner.nextDouble();
        System.out.println();

        Range range1 = new Range(from, to);

        System.out.println("Введите параметры второго диапазона");

        System.out.print("значение нижней границы: ");
        from = scanner.nextDouble();

        System.out.print("значение верхней границы: ");
        to = scanner.nextDouble();
        System.out.println();

        Range range2 = new Range(from, to);

        System.out.println("Диапазон 1: " + range1);
        System.out.printf(Locale.ENGLISH, "Длина диапазона 1: %.2f%n", range1.getLength());
        System.out.println();

        System.out.println("Диапазон 2: " + range2);
        System.out.printf(Locale.ENGLISH, "Длина диапазона 2: %.2f%n", range2.getLength());
        System.out.println();

        Range rangesIntersection = range1.getIntersection(range2);

        if (rangesIntersection == null) {
            System.out.println("У заданных диапазонов отсутствует пересечение.");
            System.out.println();
        } else {
            System.out.println("Пересечение диапазонов: " + rangesIntersection);
            System.out.println();
        }

        Range[] rangesUnion = range1.getUnion(range2);

        System.out.println("Сумма диапазонов: " + Range.print(rangesUnion));
        System.out.println();

        Range[] rangesDifference = range1.getDifference(range2);

        System.out.println("Разность диапазонов: " + Range.print(rangesDifference));
    }
}