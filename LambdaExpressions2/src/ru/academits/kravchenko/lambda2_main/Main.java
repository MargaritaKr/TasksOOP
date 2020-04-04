package ru.academits.kravchenko.lambda2_main;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Создать бесконечный поток корней чисел.
        // С консоли прочитать число – сколько элементов нужно вычислить, затем – распечатать эти элементы
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество элементов:");
        int limit = scanner.nextInt();

        Stream.iterate(1, x -> x + 1)
                .map(Math::sqrt)
                .limit(limit)
                .forEach(x -> System.out.printf("%.2f%n", x));

        System.out.println();

        // Попробовать реализовать бесконечный поток чисел Фиббоначчи
        System.out.print("Введите количество числел Фибоначчи для вывода на экран: ");
        int limitFibonacci = scanner.nextInt();

        Stream.iterate(new long[]{0, 1}, s -> new long[]{s[1], s[0] + s[1]})
                .map(n -> n[0])
                .limit(limitFibonacci)
                .forEach(System.out::println);
    }
}
