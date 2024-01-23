package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.Interval;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/insert-interval
 *
 * Given a sorted list of nonoverlapping intervals and a new interval, your task is to insert the new interval into the correct position
 * while ensuring that the resulting list of intervals remains sorted and nonoverlapping.
 *
 * Each interval is a pair of nonnegative numbers, the first being the start time and the second being the end time of the interval.
 */
public class InsertInterval {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> insert((List<Interval>) e[0], (Interval) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static List<Interval> insert(List<Interval> intervals, Interval tInterval) {
        List<Interval> results = new ArrayList<>();
        int i = 0;
        for (; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            //while interval is before tInterval
            if (interval.getStart() < tInterval.getStart()) {
                results.add(interval);
            } else {
                break;
            }

        }
        if (results.size() == 0 || results.get(results.size() - 1).getEnd() < tInterval.getStart()) {
            results.add(tInterval);
        } else {
            Interval interval = results.get(results.size() - 1);
            interval.setEnd(Math.max(interval.getEnd(), tInterval.getEnd()));
        }
        for (; i < intervals.size(); i++) {
            Interval currInterval = intervals.get(i);
            Interval mergedInterval = results.get(results.size() - 1);
            if (currInterval.getStart() <= mergedInterval.getEnd()) {
                mergedInterval.setEnd(Math.max(currInterval.getEnd(), mergedInterval.getEnd()));
            } else {
                results.add(currInterval);
            }
        }
        return results;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {List.of(Interval.from(1, 2), Interval.from(3, 5), Interval.from(6, 8)), Interval.from(5, 7)},
                {List.of(Interval.from(1, 3), Interval.from(5, 7), Interval.from(10, 12)), Interval.from(8, 9)},
                {List.of(Interval.from(8, 10), Interval.from(12, 15)), Interval.from(10, 12)},
                {List.of(Interval.from(5, 7), Interval.from(8, 9)), Interval.from(1, 3)},
                {List.of(Interval.from(3, 5)), Interval.from(1, 10)}
        };
    }
}