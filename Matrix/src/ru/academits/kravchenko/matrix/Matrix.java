package ru.academits.kravchenko.matrix;

import ru.academits.kravchenko.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("matrix sizes must be > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Vector... rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("matrix rows count must be > 0");
        }

        int vectorsLength = 0;

        for (Vector row : rows) {
            vectorsLength = Math.max(row.getSize(), vectorsLength);
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(vectorsLength);
            this.rows[i].add(rows[i]);
        }
    }

    public Matrix(double[]... rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("matrix rows count must be > 0");
        }

        int vectorsLength = 0;

        for (double[] row : rows) {
            vectorsLength = Math.max(row.length, vectorsLength);
        }

        if (vectorsLength == 0) {
            throw new IllegalArgumentException("matrix columns count must be > 0");
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(vectorsLength, rows[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < strings count");
        }

        return new Vector(rows[index]);
    }

    public void setRow(Vector vector, int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < strings count");
        }

        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("vector size must be = matrix columns count");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < row length");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(rows[i].getComponent(index), i);
        }

        return column;
    }

    public void transpose() {
        Vector[] result = new Vector[getColumnsCount()];

        for (int i = 0; i < result.length; i++) {
            result[i] = getColumn(i);
        }

        rows = result;
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalArgumentException("determinant can only be defined for a square matrix, size > 0");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) -
                    rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double det = 0;
        for (int i = 0; i < rows.length; i++) {
            Matrix addition = new Matrix(rows.length - 1, rows.length - 1);

            for (int m = 0; m < rows.length - 1; m++) {
                int j = 0;

                Vector row = new Vector(rows.length - 1);

                for (int n = 0; n < rows.length - 1; n++, j++) {
                    if (j == i) {
                        j++;
                    }

                    row.setComponent((rows[m + 1].getComponent(j)), n);
                }

                addition.setRow(row, m);
            }

            det += rows[0].getComponent(i) * Math.pow(-1, i) * addition.getDeterminant();
        }

        return det;
    }

    public void multiply(double number) {
        for (Vector row : rows) {
            row.multiply(number);
        }
    }

    public Vector getProduct(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("vector length must be = columns count");
        }

        Vector result = new Vector (rows.length);

        for (int i = 0; i < rows.length; i++) {
            result.setComponent(Vector.getScalarProduct(rows[i], vector),i);
        }

        return result;
    }

    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrices must be the same size");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getComposition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("rows of matrix1 and columns of matrix2 count must be equal");
        }

        Matrix result = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < result.rows.length; i++) {
            for (int j = 0; j < result.getColumnsCount(); j++) {
                result.rows[i].setComponent(Vector.getScalarProduct(matrix1.getRow(i), matrix2.getColumn(j)), j);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");

        for (int i = 0; i < rows.length; i++) {
            stringBuilder.append(rows[i].toString());

            if (i == rows.length - 1) {
                stringBuilder.append(" }");
                return stringBuilder.toString();
            }

            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }
}