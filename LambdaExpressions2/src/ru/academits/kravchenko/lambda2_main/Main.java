package ru.academits.kravchenko.lambda2_main;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        /*
        Создать бесконечный поток корней чисел.
        С консоли прочитать число – сколько элементов нужно вычислить,
        затем – распечатать эти элементы
         */

        Scanner scanner = new Scanner(System.in);

        DoubleStream numbersRoots = DoubleStream.iterate(1, x-> x + 1).map(Math::sqrt);

        System.out.println("Введите количество элементов:");
        int limit = scanner.nextInt();

        numbersRoots.limit(limit).forEach(x -> System.out.printf("%.2f%n", x));

        /*
        Попробовать реализовать бесконечный поток чисел Фиббоначчи
         */

        System.out.print("Введите количество числел Фибоначчи для вывода на экран: ");
        int limitFibonacci = scanner.nextInt();
/*
        LongStream fibonacciNumbers = LongStream.generate({
                () -> {
                    long x = 0;
                    long y = 1;
                    long result = 0;

                    while (true) {
                        result = x + y;
                        x = y;
                        y = result;
                    }

                    return result;
                }


        }

 */











    }
}
