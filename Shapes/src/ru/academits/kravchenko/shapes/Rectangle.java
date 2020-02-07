package ru.academits.kravchenko.shapes;

public class Rectangle implements Shapes.Shape {
    private final static int SAME_SIDES_AMOUNT = 2;
    private final static double EPSILON = 1.0e-10;

    private double firstSideLength;
    private double secondSideLength;

    public Rectangle(double firstSideLength, double secondSideLength) {
        if (firstSideLength < 0 || secondSideLength < 0) {
            throw new IllegalArgumentException("side length must be >= 0");
        }
        this.firstSideLength = firstSideLength;
        this.secondSideLength = secondSideLength;
    }

    @Override
    public double getWidth() {
        return firstSideLength;
    }

    @Override
    public double getHeight() {
        return secondSideLength;
    }

    @Override
    public double getArea() {
        return firstSideLength * secondSideLength;
    }

    @Override
    public double getPerimeter() {
        return (firstSideLength + secondSideLength) * SAME_SIDES_AMOUNT;
    }

    @Override
    public String toString() {
        return String.format("Прямоугольник со сторонами: %.2f и %.2f", firstSideLength, secondSideLength);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(getArea());
        hash = prime * hash + Double.hashCode(getPerimeter());

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Rectangle otherRectangle = (Rectangle) object;

        return (Math.abs(firstSideLength - otherRectangle.firstSideLength) <= EPSILON &&
                Math.abs(secondSideLength - otherRectangle.secondSideLength) <= EPSILON) ||
                (Math.abs(firstSideLength - otherRectangle.secondSideLength) <= EPSILON &&
                        Math.abs(secondSideLength - otherRectangle.firstSideLength) <= EPSILON);
    }
}