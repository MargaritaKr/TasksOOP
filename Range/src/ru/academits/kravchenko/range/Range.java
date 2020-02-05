package ru.academits.kravchenko.range;

public class Range {
    private final static double EPSILON = 1.0e-10;

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return Math.abs(to - from);
    }

    public boolean isInside(double presentNumber) {
        if (from < to) {
            return presentNumber > (from - EPSILON) && presentNumber < (to + EPSILON);
        }
        return presentNumber < (from - EPSILON) && presentNumber > (to + EPSILON);
    }
}