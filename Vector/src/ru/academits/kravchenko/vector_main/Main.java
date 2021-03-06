package ru.academits.kravchenko.vector_main;

import ru.academits.kravchenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест класса Vector");
        System.out.println();

        Vector vector1 = new Vector(5);

        System.out.println("Создаем вектор 1 размера 5: " + vector1);

        System.out.print("Размер: " + vector1.getSize());
        System.out.printf("      Длина вектора 1: %.2f", vector1.getVectorLength());
        System.out.println("     Хэш-код: " + vector1.hashCode());
        System.out.println();

        double[] numbers = new double[]{3, 4.5, 2};

        Vector vector2 = new Vector(numbers);

        System.out.println("Создаем вектор 2 из массива: " + vector2);

        System.out.print("Размер: " + vector2.getSize());
        System.out.printf("      Длина вектора 2: %.2f", vector2.getVectorLength());
        System.out.println("     Хэш-код: " + vector2.hashCode());
        System.out.println();

        Vector vector3 = new Vector(4, numbers);

        System.out.println("Создаем вектор 3 из того же массива, но размера 4: " + vector3);

        System.out.print("Сравним вектор 2 и вектор 3: ");

        if (vector2.equals(vector3)) {
            System.out.println("РАВНЫ");
        } else {
            System.out.println("НЕ РАВНЫ");
        }

        System.out.println();

        Vector vector4 = new Vector(vector3);

        System.out.println("Создаем вектор 4 из вектора 3: " + vector4);

        System.out.print("Сравним вектор 4 и вектор 3: ");

        if (vector4.equals(vector3)) {
            System.out.println("РАВНЫ");
        } else {
            System.out.println("НЕ РАВНЫ");
        }

        System.out.println();

        vector3.turn();

        System.out.println("Теперь поменяем вектор 3 (развернем его): " + vector3);
        System.out.println("Вектор 4 не должен измениться: " + vector4);
        System.out.println();

        System.out.printf("Изменим на число 10 последний элемент вектора 4(%.2f): ", vector4.getComponent(vector4.getSize() - 1));

        vector4.setComponent(10, vector4.getSize() - 1);

        System.out.println(vector4);
        System.out.println();

        System.out.print("Прибавим к вектору 2: " + vector2);
        System.out.println(" вектор 4: " + vector4);

        vector2.add(vector4);

        System.out.println("Теперь вектор 2: " + vector2);
        System.out.println();

        System.out.print("Отнимем от вектора 4: " + vector4);
        System.out.println(" вектор 2: " + vector2);

        vector4.subtract(vector2);

        System.out.println("Теперь вектор 4: " + vector4);
        System.out.println();

        System.out.print("Получим векторы суммы и разности вектора 2: " + vector2);
        System.out.println(" вектора 4: " + vector4);

        System.out.println("Вот они: " + Vector.getSum(vector2, vector4) + " и " + Vector.getDifference(vector2, vector4));

        System.out.print("Сами вектор 2: " + vector2);
        System.out.print(" и вектор 4: " + vector4);
        System.out.println(" не изменились.");
        System.out.println();

        System.out.println("Умножим вектор 3: " + vector3 + " на число 10 ");

        vector3.multiply(10);

        System.out.println("Скалярное произведение вектора 2: " + vector2.toString() + " и вектора 3: " + vector3);
        System.out.printf("это число: %.2f", Vector.getScalarProduct(vector2, vector3));
        System.out.println();

        vector3.add(vector3);
    }
}