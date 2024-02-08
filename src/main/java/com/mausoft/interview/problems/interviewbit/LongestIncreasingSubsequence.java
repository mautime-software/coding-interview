package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/longest-increasing-subsequence/
 *
 * Find the longest increasing subsequence of a given array of integers, A.
 * In other words, find a subsequence of array in which the subsequence's elements are in strictly increasing order, and in which the subsequence is as long as possible.
 * In this case, return the length of the longest increasing subsequence.
 */
public class LongestIncreasingSubsequence {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> longestSubsequence((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int longestSubsequence(int[] nums) { // O(n^2)
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int j = 1; j < nums.length; j++) {
            dp[j] = 1;
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            max = Math.max(dp[j], max);
        }
        return max;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 1, 5}},
                {new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}}
        };
    }
}