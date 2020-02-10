package ru.academits.kravchenko.vector;

import java.util.Locale;

public class Vector {
    private final static double EPSILON = 1.0e-10;
    private double[] components;

    public Vector(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must be >= 0");
        }

        this.components = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.components);
    }

    public Vector(double[] numbers) {
        this.components = new double[numbers.length];

        System.arraycopy(numbers, 0, components, 0, components.length);
    }

    public Vector(int size, double[] numbers) {
        this.components = new double[size];

        System.arraycopy(numbers, 0, components, 0, Math.min(numbers.length, size));
    }

    public double[] getComponents() {
        return components;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("index must be >= 0 and < vector size");
        }

        return components[index];
    }

    public void setComponent(double number, int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("index must be >= 0 and < vector size");
        }

        components[index] = number;
    }

    public int getSize() {
        return components.length;
    }

    public void addVector(Vector vector) {
        for (int i = 0; i < Math.min(components.length, vector.components.length); i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtractVector(Vector vector) {
        for (int i = 0; i < Math.min(components.length, vector.components.length); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void turn() {
        for (int i = 0; i < components.length; i++) {
            components[i] *= -1;
        }
    }

    public double getVectorLength() {
        double result = 0;

        for (double component : components) {
            result += Math.pow(component, 2);
        }

        return Math.sqrt(result);
    }


    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(Math.max(vector1.components.length, vector2.components.length), vector1.components);

        result.addVector(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(Math.max(vector1.components.length, vector2.components.length), vector1.components);

        result.subtractVector(vector2);

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }

    @Override
    public String toString() {
        if (components.length == 0) {
            return "{ }";
        }

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
            if (Math.abs(components[i] - otherVector.components[i]) <= EPSILON) {
                continue;
            }

            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (double component : components) {
            hash = prime * hash + Double.hashCode(component);
        }

        return hash;
    }
}