package ru.academits.kravchenko.rangehard;

public class Range {
    private final static double EPSILON = 1.0e-10;

    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;

        if (from > to) {  // на случай если при вводе перепутаны границы
            this.from = to;
            this.to = from;
        }
    }

    public static Range getRangesIntersection(Range firstRange, Range secondRange) {
        if (firstRange.getTo() < secondRange.getFrom() - EPSILON) {
            return null;
        }

        if (firstRange.getFrom() > secondRange.getTo() + EPSILON) {
            return null;
        }

        return new Range(Math.max(firstRange.getFrom(), secondRange.getFrom()), Math.min(firstRange.getTo(), secondRange.getTo()));
    }

    public static Range[] getRangesSum(Range firstRange, Range secondRange) {
        if (getRangesIntersection(firstRange, secondRange) == null) {
            Range[] result = new Range[2];
            result[0] = firstRange;
            result[1] = secondRange;

            return result;
        }

        Range[] result = new Range[1];
        result[0] = new Range(Math.min(firstRange.getFrom(), secondRange.getFrom()), Math.max(firstRange.getTo(), secondRange.getTo()));

        return result;
    }

    public static Range[] getRangesResidual(Range firstRange, Range secondRange) {
        if (getRangesIntersection(firstRange, secondRange) == null) {
            Range[] result = new Range[1];
            result[0] = firstRange;

            return result;
        }

        double firstFrom = firstRange.getFrom();
        double firstTo = firstRange.getTo();
        double secondFrom = secondRange.getFrom();
        double secondTo = secondRange.getTo();

        if (firstFrom < secondFrom - EPSILON && firstTo < secondTo - EPSILON) {
            Range[] result = new Range[1];
            result[0] = new Range(firstFrom, secondFrom);

            return result;
        }

        if (firstFrom > secondFrom + EPSILON && firstTo > secondTo + EPSILON) {
            Range[] result = new Range[1];
            result[0] = new Range(secondTo, firstTo);

            return result;
        }

        if (firstFrom < secondFrom - EPSILON && firstTo > secondTo + EPSILON) {
            Range[] result = new Range[2];
            result[0] = new Range(firstFrom, secondFrom);
            result[1] = new Range(secondTo, firstTo);

            return result;
        }

        return null;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double presentNumber) {
        if (from < to) {
            return presentNumber > (from - EPSILON) && presentNumber < (to + EPSILON);
        }
        return presentNumber < (from - EPSILON) && presentNumber > (to + EPSILON);
    }
}