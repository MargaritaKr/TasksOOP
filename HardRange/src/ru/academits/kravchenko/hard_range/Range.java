package ru.academits.kravchenko.hard_range;

import java.util.Locale;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
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

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (to <= range.from || from >= range.to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (to < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        if (from > range.to) {
            return new Range[]{new Range(range.from, range.to), new Range(from, to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (to < range.from || from > range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from < range.from && to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (from >= range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        return new Range[]{};
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "(%.2f; %.2f)", from, to);
    }

    public static String print(Range[] ranges) {
        if (ranges.length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < ranges.length; i++) {
            stringBuilder.append(ranges[i].toString());

            if (i == ranges.length - 1) {
                stringBuilder.append("]");
                break;
            }

            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }
}