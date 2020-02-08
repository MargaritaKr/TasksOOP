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

    public boolean isInside(double presentNumber) {
        return presentNumber >= from && presentNumber <= to;
    }

    public Range getIntersection(Range range) {
        if (to <= range.from) {
            return null;
        }

        if (from >= range.to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (to < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (to < range.from) {
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

    public static String printRange(Range range) {
        return String.format(Locale.ENGLISH,"(%.2f; %.2f)", range.from, range.to);
    }

    public static String printRange(Range[] ranges) {
        if (ranges.length == 2) {
            return String.format(Locale.ENGLISH,"[(%.2f; %.2f),(%.2f; %.2f)]", ranges[0].from, ranges[0].to, ranges[1].from, ranges[1].to);
        }

        if (ranges.length == 1) {
            return String.format(Locale.ENGLISH,"[(%.2f; %.2f)]", ranges[0].from, ranges[0].to);
        }

        return "[]";
    }
}
