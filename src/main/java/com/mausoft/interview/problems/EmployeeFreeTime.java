package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.Interval;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/employee-free-time
 *
 * You’re given a list containing the schedules of multiple employees. Each person’s schedule is a list of non-overlapping intervals in sorted order.
 * An interval is specified with the start and end time, both being positive integers.
 * Your task is to find the list of finite intervals representing the free time for all the employees.
 */
public class EmployeeFreeTime {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findAvailableTime((List<List<Interval>>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static List<Interval> findAvailableTime(List<List<Interval>> schedules) {
        List<Interval> intervals = schedules.stream().flatMap(Collection::stream).collect(Collectors.toList());
        intervals.sort(Comparator.comparingInt(Interval::getStart)); //sort
        Interval mergedInterval = new Interval(intervals.get(0).getStart(), intervals.get(0).getEnd());
        List<Interval> available = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.getStart() <= mergedInterval.getEnd()) {
                mergedInterval.setEnd(Math.max(mergedInterval.getEnd(), interval.getEnd()));
            } else {
                available.add(new Interval(mergedInterval.getEnd(), interval.getStart()));
                mergedInterval = new Interval(interval.getStart(), interval.getEnd());
            }
        }
        return available;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {List.of(List.of(Interval.from(1, 2), Interval.from(5, 6)),
                        List.of(Interval.from(1, 3)),
                        List.of(Interval.from(4, 6)))},
                {List.of(List.of(Interval.from(1, 3), Interval.from(6, 7)),
                        List.of(Interval.from(2, 4)),
                        List.of(Interval.from(2, 5), Interval.from(9, 12)))},
                {List.of(List.of(Interval.from(2, 3), Interval.from(7, 9)),
                        List.of(Interval.from(1, 4), Interval.from(6, 7)))},
                {List.of(List.of(Interval.from(3, 5), Interval.from(8, 10)),
                        List.of(Interval.from(4, 6), Interval.from(9, 12)),
                        List.of(Interval.from(5, 6), Interval.from(8, 10)))},
                {List.of(List.of(Interval.from(1, 3), Interval.from(6, 9), Interval.from(10, 11)),
                        List.of(Interval.from(3, 4), Interval.from(7, 12)),
                        List.of(Interval.from(1, 3), Interval.from(7, 10)),
                        List.of(Interval.from(1, 4)),
                        List.of(Interval.from(7, 10), Interval.from(11, 12)))},
                {List.of(List.of(Interval.from(1, 2), Interval.from(3, 4), Interval.from(5, 6), Interval.from(7, 8)),
                        List.of(Interval.from(2, 3), Interval.from(4, 5), Interval.from(6, 8)))},
                {List.of(List.of(Interval.from(1, 2), Interval.from(3, 4), Interval.from(5, 6), Interval.from(7, 8), Interval.from(9, 10), Interval.from(11, 12)),
                        List.of(Interval.from(1, 2), Interval.from(3, 4), Interval.from(5, 6), Interval.from(7, 8), Interval.from(9, 10), Interval.from(11, 12)),
                        List.of(Interval.from(1, 2), Interval.from(3, 4), Interval.from(5, 6), Interval.from(7, 8), Interval.from(9, 10), Interval.from(11, 12)),
                        List.of(Interval.from(1, 2), Interval.from(3, 4), Interval.from(5, 6), Interval.from(7, 8), Interval.from(9, 10), Interval.from(11, 12)))}
        };
    }
}