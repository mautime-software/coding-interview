package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/minimum-size-subarray-sum
 *
 * Given an array of positive integers, nums, and a positive integer, target, find the minimum length of a contiguous subarray
 * whose sum is greater than or equal to the target. If no such subarray is found, return 0.
 */
public class MinimumSizeSubarraySum {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findMinSubarray((int[]) e[0], (Integer) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int findMinSubarray(int[] nums, int target) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j];
            while (i <= j && sum >= target) {
                min = Math.min(min, j - i + 1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return min != Integer.MAX_VALUE ? min : 0;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{2, 3, 1, 2, 4, 3}, 7},
                {new int[]{1, 4, 4}, 4},
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 11},
                {new int[]{1, 2, 3, 4}, 10},
                {new int[]{1, 2, 1, 3}, 5},
                {new int[]{5, 4, 9, 8, 11, 3, 7, 12, 15, 44}, 15}
        };
    }
}