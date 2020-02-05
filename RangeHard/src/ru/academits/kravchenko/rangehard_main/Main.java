package ru.academits.kravchenko.rangehard_main;

import ru.academits.kravchenko.rangehard.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите параметры первого диапазона :");

        System.out.print("значение нижней границы - ");
        double lowerBound = scanner.nextDouble();

        System.out.print("значение верхней границы - ");
        double upperBound = scanner.nextDouble();
        System.out.println();

        Range firstDiapason = new Range(lowerBound, upperBound);

        System.out.println("Введите параметры второго диапазона :");

        System.out.print("значение нижней границы - ");
        lowerBound = scanner.nextDouble();

        System.out.print("значение верхней границы - ");
        upperBound = scanner.nextDouble();
        System.out.println();

        Range secondDiapason = new Range(lowerBound, upperBound);

        Range rangesIntersection = Range.getRangesIntersection(firstDiapason, secondDiapason);

        if (rangesIntersection == null) {
            System.out.println("У заданных диапазонов отсутствует пересечение.");
            System.out.println();
        } else {
            System.out.printf("Пересечение диапазонов : [%.2f; %.2f]%n", rangesIntersection.getFrom(), rangesIntersection.getTo());
            System.out.println();
        }

        Range[] rangesSum = Range.getRangesSum(firstDiapason, secondDiapason);

        System.out.println("Сумма диапазонов:");

        for (Range range : rangesSum) {
            System.out.printf("интервал [%.2f; %.2f]%n", range.getFrom(), range.getTo());
        }
        System.out.println();

        Range[] rangesResidual = Range.getRangesResidual(firstDiapason, secondDiapason);

        if (rangesResidual == null) {
            System.out.println("Разность диапазонов - пустой интервал,");
            System.out.println("так как все элементы первого диапазона входят во второй диапазон.");
        } else {
            System.out.println("Разность диапазонов:");

            for (Range range : rangesResidual) {
                System.out.printf("интервал [%.2f; %.2f]%n", range.getFrom(), range.getTo());
            }
        }
    }
}