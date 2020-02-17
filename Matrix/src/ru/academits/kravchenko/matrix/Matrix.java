package ru.academits.kravchenko.matrix;

import ru.academits.kravchenko.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] strings;

    public Matrix(int size1, int size2) {
        if (size1 <= 0 || size2 <= 0) {
            throw new IllegalArgumentException("matrix sizes must be > 0");
        }

        strings = new Vector[size1];

        Vector string = new Vector(size2);

        for (int i = 0; i < size1; i++) {
            strings[i] = string;
        }
    }

    public Matrix(Vector... strings) {
        if (strings.length == 0) {
            throw new IllegalArgumentException("matrix strings count must be > 0");
        }

        this.strings = new Vector[strings.length];

        int vectorslength = 0;

        for (Vector string : strings) {
            vectorslength = Math.max(string.getSize(), vectorslength);
        }

        for (int i = 0; i < strings.length; i++) {
            this.strings[i] = new Vector(vectorslength);
            this.strings[i].add(strings[i]);
        }
    }

    public Matrix(double[]... strings) {
        if (strings.length == 0) {
            throw new IllegalArgumentException("matrix strings count must be > 0");
        }

        this.strings = new Vector[strings.length];

        int vectorslength = 0;

        for (double[] string : strings) {
            if (string.length == 0){
                throw new IllegalArgumentException("matrix strings length must be > 0");
            }

            vectorslength = Math.max(string.length, vectorslength);
        }

        for (int i = 0; i < strings.length; i++) {
            this.strings[i] = new Vector(vectorslength, strings[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.strings);
    }

    public int[] getSizes() {
        return new int[]{strings.length, strings[0].getSize()};
    }

    public int getColumnsCount() {
        return strings[0].getSize();
    }

    public int getStringsCount() {
        return strings.length;
    }

    public Vector getString(int index) {
        if (index < 0 || index >= strings.length) {
            throw new IllegalArgumentException("index must be >= 0 and < strings count");
        }

        return strings[index];
    }

    public void setString(Vector vector, int index) {
        if (index < 0 || index >= strings.length) {
            throw new IllegalArgumentException("index must be >= 0 and < strings count");
        }

        if (vector.getSize() != strings[0].getSize()) {
            throw new IllegalArgumentException("vector size must be = matrix columns count");
        }

        strings[index] = vector;
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= strings[0].getSize()) {
            throw new IllegalArgumentException("index must be >= 0 and < string length");
        }

        Vector column = new Vector(strings.length);

        for (int i = 0; i < strings.length; i++) {
            column.setComponent(strings[i].getComponent(index), i);
        }

        return column;
    }

    public void transpose() {
        Matrix result = new Matrix(strings[0].getSize(), strings.length);

        for (int i = 0; i < result.strings.length; i++) {
            result.strings[i] = getColumn(i);
        }

        strings = result.strings;
    }

    public double getDeterminant() {
        if (strings.length != strings[0].getSize()) {
            throw new IllegalArgumentException("determinant can only be defined for a square matrix, size > 0");
        }

        if (strings.length == 1) {
            return strings[0].getComponent(0);
        }

        if (strings.length == 2) {
            return strings[0].getComponent(0) * strings[1].getComponent(1) -
                    strings[0].getComponent(1) * strings[1].getComponent(0);
        }

        double det = 0;
        for (int i = 0; i < strings.length; i++) {
            Matrix addition = new Matrix(strings.length - 1, strings.length - 1);

            for (int m = 0; m < strings.length - 1; m++) {
                int j = 0;

                Vector string = new Vector(strings.length - 1);

                for (int n = 0; n < strings.length - 1; n++, j++) {
                    if (j == i) {
                        j++;
                    }

                    string.setComponent((strings[m + 1].getComponent(j)), n);
                }

                addition.setString(string, m);
            }

            det += strings[0].getComponent(i) * Math.pow(-1, i) * addition.getDeterminant();
        }

        return det;
    }

    public void multiply(double number) {
        for (Vector string : strings) {
            string.multiply(number);
        }
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != strings[0].getSize()) {
            throw new IllegalArgumentException("vector length must be = columns count");
        }

        Vector result = new Vector(strings.length);

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < vector.getSize(); j++) {

                result.setComponent(result.getComponent(i) + strings[i].getComponent(j) * vector.getComponent(j), i);
            }
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (!Arrays.equals(getSizes(), matrix.getSizes())) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        for (int i = 0; i < strings.length; i++) {
            strings[i].add(matrix.getString(i));
        }
    }

    public void subtract(Matrix matrix) {
        if (!Arrays.equals(getSizes(), matrix.getSizes())) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        for (int i = 0; i < strings.length; i++) {
            strings[i].subtract(matrix.getString(i));
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (!Arrays.equals(matrix1.getSizes(), matrix2.getSizes())) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (!Arrays.equals(matrix1.getSizes(), matrix2.getSizes())) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getComposition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getStringsCount()) {
            throw new IllegalArgumentException("strings of matrix1 and columns of matrix2 count must be equal");
        }

        Matrix result = new Matrix(matrix1.getStringsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < result.strings.length; i++) {

            Vector string = new Vector(result.getColumnsCount());

            for (int j = 0; j < result.strings[0].getSize(); j++) {
                string.setComponent(Vector.getScalarProduct(matrix1.getString(i), matrix2.getColumn(j)), j);
            }

            result.setString(string, i);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder matrixToString = new StringBuilder("{ ");

        for (int i = 0; i < strings.length; i++) {
            matrixToString.append(strings[i].toString());

            if (i == strings.length - 1) {
                matrixToString.append(" }");
                return matrixToString.toString();
            }

            matrixToString.append(", ");
        }

        return matrixToString.toString();
    }
}