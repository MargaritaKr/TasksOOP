package ru.academits.kravchenko.shape;

public class Triangle implements Shape {
    private final static double EPSILON = 1.0e-10;

    private double x1;
    private double y1;

    private double x2;
    private double y2;

    private double x3;
    private double y3;

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
    }

    private double[] getSideLengths() {
        double length1 = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        double length2 = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
        double length3 = Math.sqrt(Math.pow((x2 - x3), 2) + Math.pow((y2 - y3), 2));

        return new double[]{length1, length2, length3};
    }

    private double getDistanceByAxis(double a, double b, double c) {
        return Math.max(Math.max(a, b), c) - Math.min(Math.min(a, b), c);
    }

    @Override
    public double getWidth() {
        return getDistanceByAxis(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getDistanceByAxis(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;

        return Math.sqrt(halfPerimeter * (halfPerimeter - getSideLengths()[0]) * (halfPerimeter - getSideLengths()[1]) * (halfPerimeter - getSideLengths()[2]));
    }

    @Override
    public double getPerimeter() {
        return getSideLengths()[0] + getSideLengths()[1] + getSideLengths()[2];
    }

    @Override
    public String toString() {
        return String.format("Треугольник, заданный координатами: (%.2f; %.2f),(%.2f; %.2f),(%.2f; %.2f)", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

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

        Triangle otherTriangle = (Triangle) object;

        return (x1 == otherTriangle.x1 && y1 == otherTriangle.y1 && x2 == otherTriangle.x2 && y2 == otherTriangle.y2 &&
                x3 == otherTriangle.x3 && y3 == otherTriangle.y3);
    }
}