package ru.academits.kravchenko.shapes;

import java.util.Arrays;

public class Triangle implements Shapes.Shape {
    private final static double EPSILON = 1.0e-10;

    private double x1;
    private double y1;

    private double x2;
    private double y2;

    private double x3;
    private double y3;

    private double length1;
    private double length2;
    private double length3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) <= EPSILON) {
            throw new IllegalArgumentException("points can’t be on one line");
        }
        this.x1 = x1;
        this.y1 = y1;

        this.x2 = x2;
        this.y2 = y2;

        this.x3 = x3;
        this.y3 = y3;

        this.length1 = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        this.length2 = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
        this.length3 = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double halfPerimeter = this.getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - length1) * (halfPerimeter - length2) * (halfPerimeter - length3));
    }

    @Override
    public double getPerimeter() {
        return length1 + length2 + length3;
    }

    @Override
    public String toString() {
        return String.format("Треугольник, заданный координатами: (%.2f; %.2f),(%.2f; %.2f),(%.2f; %.2f)", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(getPerimeter());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Triangle otherTriangle = (Triangle) object;

        double[] sideLengths1 = new double[]{length1, length2, length3};
        double[] sideLengths2 = new double[]{otherTriangle.length1, otherTriangle.length2, otherTriangle.length3};

        Arrays.sort(sideLengths1);
        Arrays.sort(sideLengths2);

        return Math.abs(sideLengths1[0] - sideLengths2[0])<=EPSILON &&
                Math.abs(sideLengths1[1] - sideLengths2[1])<=EPSILON &&
                Math.abs(sideLengths1[2] - sideLengths2[2])<=EPSILON;
    }
}