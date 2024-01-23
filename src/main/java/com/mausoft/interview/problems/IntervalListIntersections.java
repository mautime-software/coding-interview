package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.Interval;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * For two lists of closed intervals given as input, intervalLista and intervalListb, where each interval has its own start and end time,
 * write a function that returns the intersection of the two interval lists.
 *
 * For example, the intersection of [3, 8] and [5, 10] is [5, 8]
 */
public class IntervalListIntersections {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findIntersections((List<Interval>) e[0], (List<Interval>) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static List<Interval> findIntersections(List<Interval> iIntervals, List<Interval> jIntervals) {
        int i = 0;
        int j = 0;
        List<Interval> intersections = new ArrayList<>();
        while (i < iIntervals.size() && j < jIntervals.size()) {
            int start = Math.max(iIntervals.get(i).getStart(), jIntervals.get(j).getStart());
            int end = Math.min(iIntervals.get(i).getEnd(), jIntervals.get(j).getEnd());
            if (start <= end) {
                intersections.add(new Interval(start, end));
            }
            if (iIntervals.get(i).getEnd() < jIntervals.get(j).getEnd()) {
                i++;
            } else {
                j++;
            }
        }
        return intersections;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {List.of(Interval.from(1, 4), Interval.from(5, 6), Interval.from(7, 9)),
                        List.of(Interval.from(3, 5), Interval.from(6, 7), Interval.from(8, 9))
                },
                {List.of(Interval.from(1, 2)), List.of(Interval.from(1, 2))},
                {List.of(Interval.from(1, 4), Interval.from(5, 6), Interval.from(9, 15)),
                        List.of(Interval.from(2, 4), Interval.from(5, 7), Interval.from(9, 15))
                },
                {List.of(Interval.from(4, 7), Interval.from(9, 16), Interval.from(17, 28), Interval.from(39, 50), Interval.from(55, 66), Interval.from(70, 89)),
                        List.of(Interval.from(3, 6), Interval.from(7, 8), Interval.from(9, 10), Interval.from(14, 19), Interval.from(23, 33), Interval.from(35, 40), Interval.from(45, 59), Interval.from(60, 64), Interval.from(68, 76))},
                {List.of(Interval.from(1, 3), Interval.from(5, 6), Interval.from(7, 8), Interval.from(12, 15)),
                        List.of(Interval.from(2, 4), Interval.from(7, 10))}
        };
    }
}