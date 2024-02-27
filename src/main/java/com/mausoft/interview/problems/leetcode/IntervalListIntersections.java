package com.mausoft.interview.problems.leetcode;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/interval-list-intersections
 *
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 */
public class IntervalListIntersections {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> intersections((int[][]) e[0], (int[][]) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int[][] intersections(int[][] first, int [][] second) {
        int i = 0;
        int j = 0;
        List<Integer[]> intersections = new ArrayList<>();
        while (i < first.length && j < second.length) {
            int start = Math.max(first[i][0], second[j][0]);
            int end = Math.min(first[i][1], second[j][1]);
            if (start <= end) {
                intersections.add(new Integer[]{start, end});
            }
            if (first[i][1] < second[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        int[][] results = new int[intersections.size()][2];
        for (int k = 0; k < intersections.size(); k++) {
            results[k] = new int[]{intersections.get(k)[0], intersections.get(k)[1]};
        }
        return results;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{0,2},{5,10},{13,23},{24,25}}, new int[][]{{1,5},{8,12},{15,24},{25,26}}},
                {new int[][]{{1,3}}, new int[][]{{5,9}}}
        };
    }
}