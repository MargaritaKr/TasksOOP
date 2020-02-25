package ru.academits.kravchenko.vector;

import java.util.Arrays;
import java.util.Locale;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.components);
    }

    public Vector(double... numbers) {
        this(numbers.length, numbers);
    }

    public Vector(int size, double[] numbers) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }

        components = Arrays.copyOf(numbers, size);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < vector size");
        }

        return components[index];
    }

    public void setComponent(double number, int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < vector size");
        }

        components[index] = number;
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (vector.components.length > components.length) {
            double[] result = new double[vector.components.length];
            System.arraycopy(components, 0, result, 0, components.length);
            components = result;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length) {
            double[] result = new double[vector.components.length];
            System.arraycopy(components, 0, result, 0, components.length);
            components = result;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void turn() {
        multiply(-1);
    }

    public double getVectorLength() {
        double result = 0;

        for (double component : components) {
            result += Math.pow(component, 2);
        }

        return Math.sqrt(result);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        result.subtract(vector2);

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder vectorToString = new StringBuilder("{ ");

        for (int i = 0; i < components.length; i++) {
            vectorToString.append(String.format(Locale.ENGLISH, "%.2f", components[i]));

            if (i == components.length - 1) {
                vectorToString.append(" }");
                return vectorToString.toString();
            }

            vectorToString.append(", ");
        }

        return vectorToString.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector otherVector = (Vector) object;

        if (components.length != otherVector.components.length) {
            return false;
        }

        for (int i = 0; i < components.length; i++) {
            if (components[i] != otherVector.components[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}