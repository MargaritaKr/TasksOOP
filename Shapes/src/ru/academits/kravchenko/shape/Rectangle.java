package ru.academits.kravchenko.shape;

public class Rectangle implements Shape {
    private final static int SAME_SIDES_AMOUNT = 2;
    private final static double EPSILON = 1.0e-10;

    private double width;
    private double height;

    public Rectangle(double firstSideLength, double secondSideLength) {
        if (firstSideLength <= 0 || secondSideLength <= 0) {
            throw new IllegalArgumentException("side length must be > 0");
        }

        this.width = firstSideLength;
        this.height = secondSideLength;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * SAME_SIDES_AMOUNT;
    }

    @Override
    public String toString() {
        return String.format("Прямоугольник со сторонами: %.2f и %.2f", width, height);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(Math.min(width, height));
        hash = prime * hash + Double.hashCode(Math.max(width, height));

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Rectangle otherRectangle = (Rectangle) object;

        return (Math.abs(width - otherRectangle.width) <= EPSILON &&
                Math.abs(height - otherRectangle.height) <= EPSILON) ||
                (Math.abs(width - otherRectangle.height) <= EPSILON &&
                        Math.abs(height - otherRectangle.width) <= EPSILON);
    }
}