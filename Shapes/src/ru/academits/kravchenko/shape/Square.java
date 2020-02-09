package ru.academits.kravchenko.shape;

public class Square implements Shape {
    private final static int SIDES_AMOUNT = 4;
    private final static double EPSILON = 1.0e-10;

    private double sideLength;

    public Square(double sideLength) {
        if (sideLength <= 0) {
            throw new IllegalArgumentException("side length must be > 0");
        }

        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * SIDES_AMOUNT;
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной: %.2f", sideLength);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(sideLength);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Square otherSquare = (Square) object;

        return Math.abs(sideLength - otherSquare.sideLength) <= EPSILON;
    }
}