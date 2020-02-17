package ru.academits.kravchenko.matrix_main;

import ru.academits.kravchenko.matrix.Matrix;
import ru.academits.kravchenko.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест класса Matrix");
        System.out.println();

        Matrix matrix1 = new Matrix(2, 3);

        System.out.print("Создаем матрицу 1 (нулей) размера 2 х 3: " + matrix1);
        System.out.println(", размер: " + Arrays.toString(matrix1.getSizes()));
        System.out.println();

        System.out.println("Создаем несколько векторов разного размера:");

        Vector vector1 = new Vector(2, 5);
        Vector vector2 = new Vector(4.5);
        Vector vector3 = new Vector(1.5, 6.7, 5.9, 8);
        Vector vector4 = new Vector(7, 11.5, 2);

        System.out.println("Вектор1: " + vector1);
        System.out.println("Вектор2: " + vector2);
        System.out.println("Вектор3: " + vector3);
        System.out.println("Вектор4: " + vector4);
        System.out.println();

        Matrix matrix2 = new Matrix(vector1, vector2, vector3, vector4);

        System.out.println("Создаем матрицу 2 из массива векторов разного размера (строки дополнены нулями):");
        System.out.print(matrix2);
        System.out.println(", размер: " + Arrays.toString(matrix2.getSizes()));
        System.out.println("Вычислим определитель квадратной матирцы 2. Он равен: " + matrix2.getDeterminant());
        System.out.println();

        double[][] numbers = new double[][]{{2, -3, 1, 0.6}, {5, 4, -2, 4}, {7, 9, 10, 11}};

        Matrix matrix3 = new Matrix(numbers);

        System.out.print("Создаем матрицу 3 из двумерного массива чисел: " + matrix3);
        System.out.println();

        Matrix matrix4 = new Matrix(matrix3);

        System.out.print("Создаем матрицу 4 путем копирования матрицы 3: " + matrix4);
        System.out.println();

        System.out.println("Поменяем первые две строки матрицы 4 на первый столбец из матрицы 2 и первую строку из матрицы 3.");

        matrix4.setString(matrix2.getColumn(0), 0);
        matrix4.setString(matrix3.getString(0), 1);

        System.out.println("Теперь матрица 4 выглядет так: " + matrix4);
        System.out.println();

        System.out.println("Матрица 3 не изменилась: " + matrix3);
        System.out.print("Количество строк: " + matrix3.getStringsCount());
        System.out.println(", количество столбцов: " + matrix3.getColumnsCount());
        System.out.println();

        matrix3.transpose();

        System.out.println("Матрица 3 транспонированная: " + matrix3);
        System.out.print("Количество строк: " + matrix3.getStringsCount());
        System.out.println(", количество столбцов: " + matrix3.getColumnsCount());
        System.out.println();

        System.out.print("Умножим матрицу 3 на число -5: ");

        matrix3.multiply(-5);

        System.out.println(matrix3);
        System.out.println();

        System.out.print("А теперь умножим матрицу 3 на вектор 4 " + vector4.toString() + ", ");
        System.out.println("результирующий вектор: " + matrix3.multiply(vector4));
        System.out.println();

        Matrix matrix5 = new Matrix(new double[][]{{2, 3, 4}, {5, 6, 7}});
        Matrix matrix6 = new Matrix(new double[][]{{1.2, 3.4, 4.5}, {50, -6, 7.1}});

        System.out.print("Прибавим к матрице 5: " + matrix5);
        System.out.println(" матрицу 6: " + matrix6);

        matrix5.add(matrix6);

        System.out.println("Теперь матрица 5: " + matrix5);
        System.out.println();

        Matrix matrix7 = new Matrix(new double[][]{{3, 1, 6}, {9, -2, 7}});
        System.out.println("Отнимем от матрицы 5 матрицу 7: " + matrix7);

        matrix5.subtract(matrix7);

        System.out.println("Теперь матрица 5: " + matrix5);
        System.out.println();

        System.out.println("Это матрица суммы матриц 5 и 6: " + Matrix.getSum(matrix5, matrix6));
        System.out.println("Сами матрицы 5 и 6 не изменились: " + matrix5 + " и " + matrix6);
        System.out.println();

        System.out.println("А это матрица их разности: " + Matrix.getDifference(matrix5, matrix6));
        System.out.println();

        System.out.print("Транспонируем матрицу 5, чтобы получить произведение этих матриц: ");

        matrix5.transpose();

        System.out.println(matrix5);
        System.out.println();

        System.out.print("Матрица произведения матрицы 5 и матрицы 6: ");

        Matrix matrix8 = Matrix.getComposition(matrix5, matrix6);

        System.out.print(matrix8);
        System.out.println(", ее размер: " + Arrays.toString(matrix8.getSizes()));
        System.out.println();

        System.out.print("А матрица произведения матрицы 6 и матрицы 5: ");

        Matrix matrix9 = Matrix.getComposition(matrix6, matrix5);

        System.out.print(matrix9);
        System.out.println(", ее размер: " + Arrays.toString(matrix9.getSizes()));
    }
}