package ru.academits.kravchenko.vector_main;

import ru.academits.kravchenko.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);

        System.out.println("Создаем вектор 1 размера 5: " + vector1.toString());

        System.out.print("Размер: " + vector1.getSize());
        System.out.printf("      Длина вектора 1: %.2f", vector1.getVectorLength());
        System.out.println("     Хэш-код: " + vector1.hashCode());
        System.out.println();

        double[] numbers = new double[]{3, 4.5, 2};

        Vector vector2 = new Vector(numbers);

        System.out.println("Создаем вектор 2 из массива: " + vector2.toString());

        System.out.print("Размер: " + vector2.getSize());
        System.out.printf("      Длина вектора 2: %.2f", vector2.getVectorLength());
        System.out.println("     Хэш-код: " + vector2.hashCode());
        System.out.println();

        Vector vector3 = new Vector(4, numbers);

        System.out.println("Создаем вектор 3 из того же массива, но размера 4: " + vector3.toString());

        System.out.print("Сравним вектор 1 и вектор 3: ");

        if (vector2.equals(vector3)) {
            System.out.println("РАВНЫ");
        } else {
            System.out.println("НЕ РАВНЫ");
        }

        System.out.println();

        Vector vector4 = new Vector(vector3);

        System.out.println("Создаем вектор 4 из вектора 3: " + vector4.toString());

        System.out.print("Сравним вектор 4 и вектор 3: ");

        if (vector4.equals(vector3)) {
            System.out.println("РАВНЫ");
        } else {
            System.out.println("НЕ РАВНЫ");
        }

        System.out.println();

        vector3.turn();

        System.out.println("Теперь поменяем вектор 3 (развернем его): " + vector3.toString());
        System.out.println("Вектор 4 не должен измениться: " + vector4.toString());
        System.out.println();

        System.out.printf("Изменим на число 10 последний элемент вектора 4(%.2f): ", vector4.getComponent(vector4.getSize() - 1));

        vector4.setComponent(10, vector4.getSize() - 1);

        System.out.println(vector4.toString());
        System.out.println();

        System.out.print("Прибавим вектор 2: " + vector2.toString());
        System.out.println(" к вектору вектору 4: " + vector4.toString());

        vector2.addVector(vector4);

        System.out.println("Теперь вектор 2: " + vector2.toString());
        System.out.println();

        System.out.print("Отнимем от вектора 4: " + vector4.toString());
        System.out.println(" вектор 2: " + vector2.toString());

        vector4.subtractVector(vector2);

        System.out.println("Теперь вектор 4: " + vector4.toString());
        System.out.println();

        System.out.print("Получим векторы суммы и разности вектора 2: " + vector2.toString());
        System.out.println(" вектора 4: " + vector4.toString());

        System.out.println("Вот они: " + Vector.getSum(vector2, vector4).toString() + " и " + Vector.getDifference(vector2, vector4).toString());

        System.out.print("Сами вектор 2: " + vector2.toString());
        System.out.print(" и вектор 4: " + vector4.toString());
        System.out.println(" не изменились.");
        System.out.println();

        System.out.println("Умножим вектор 3: " + vector3.toString() + " на число 10 ");

        vector3.multiply(10);

        System.out.println("Распечатаем массив элементов вектора 3: " + Arrays.toString(vector3.getComponents()));
        System.out.println();

        System.out.println("Скалярное произведение вектора 2: " + vector2.toString() + " и вектора 3: " + vector3.toString());
        System.out.printf("это число: %.2f", Vector.getScalarProduct(vector2, vector3));
        System.out.println();

        System.out.println("Name of the current class:" + vector1.getClass());

    }
}