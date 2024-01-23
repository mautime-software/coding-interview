package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.Interval;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/merge-intervals
 */
public class MergeIntervals {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> merge((List<Interval>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static List<Interval> merge(List<Interval> intervals) {
        Interval mergedInterval = new Interval(intervals.get(0).getStart(), intervals.get(0).getEnd());
        List<Interval> merged = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.getStart() <= mergedInterval.getEnd()) {
                mergedInterval.setEnd(Math.max(interval.getEnd(), mergedInterval.getEnd()));
            } else {
                merged.add(mergedInterval);
                mergedInterval = new Interval(interval.getStart(), interval.getEnd());
            }
        }
        merged.add(mergedInterval);
        return merged;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {List.of(Interval.from(1, 5), Interval.from(3, 7), Interval.from(4, 6))},
                {List.of(Interval.from(1, 5), Interval.from(4, 6), Interval.from(6, 8), Interval.from(11, 15))},
                {List.of(Interval.from(3, 7), Interval.from(6, 8), Interval.from(10, 12), Interval.from(11, 15))},
                {List.of(Interval.from(1, 5))},
                {List.of(Interval.from(1, 9), Interval.from(3, 8), Interval.from(4, 4))},
                {List.of(Interval.from(1, 2), Interval.from(3, 4), Interval.from(8, 8))},
                {List.of(Interval.from(1, 5), Interval.from(1, 3))},
                {List.of(Interval.from(1, 5), Interval.from(6, 9))},
                {List.of(Interval.from(0, 0), Interval.from(1, 18), Interval.from(1, 3))}
        };
    }
}