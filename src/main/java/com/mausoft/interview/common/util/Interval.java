package com.mausoft.interview.common.util;

public class Interval {
    private int start;
    private int end;

    public Interval(int aStart, int aEnd) {
        start = aStart;
        end = aEnd;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setStart(int aStart) {
        start = aStart;
    }

    public void setEnd(int aEnd) {
        end = aEnd;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", start, end);
    }

    public static Interval from(int aStart, int aEnd) {
        return new Interval(aStart, aEnd);
    }
}