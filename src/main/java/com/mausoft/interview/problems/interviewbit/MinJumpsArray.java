package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/min-jumps-array/
 *
 * Given an array of non-negative integers, A, of length N, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Return the minimum number of jumps required to reach the last index.
 * If it is not possible to reach the last index, return -1.
 *
 * NOTE: The more optimal solution can be achieved by using a greedy approach known as the "greedy best-first search".
 *
 * TODO - Improve recursive solution using dynamic programming
 * TODO - Implement a more performant solution O(n^2)
 */
public class MinJumpsArray {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> RecursiveSolution.minJumps((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    private static class RecursiveSolution {
        public static int minJumps(int[] nums) {
            return minJumpsRecursive(nums, 0);
        }

        private static int minJumpsRecursive(int[] nums, int i) { //O(2^n) exponential
            if (i >= nums.length - 1) {
                return 0;
            }
            if (nums[i] == 0) {
                return -1;
            }
            int farthestJump = Math.min(nums[i] + i, nums.length - 1);
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j <= farthestJump; j++) {
                int c1 = minJumpsRecursive(nums, j);
                if (c1 != -1) {
                    min = Math.min(min, c1 + 1);
                }
            }
            return min != Integer.MAX_VALUE ? min : -1;
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{2, 1, 1}},
                {new int[]{2, 3, 1, 1, 4}},
                {new int[]{1, 2, 3, 4, 5}},
                {new int[]{5, 4, 3, 2, 1}},
                {new int[]{3, 2, 1, 0, 4}},
                {new int[]{0, 46, 46, 0, 2, 47, 1, 24, 45, 0, 0, 24, 18, 29, 27, 11, 0, 0, 40, 12, 4, 0, 0, 0, 49, 42, 13, 5, 12, 45, 10, 0, 29, 11, 22, 15, 17, 41, 34, 23, 11, 35, 0, 18, 47, 0, 38, 37, 3, 37, 0, 43, 50, 0, 25, 12, 0, 38, 28, 37, 5, 4, 12, 23, 31, 9, 26, 19, 11, 21, 0, 0, 40, 18, 44, 0, 0, 0, 0, 30, 26, 37, 0, 26, 39, 10, 1, 0, 0, 3, 50, 46, 1, 38, 38, 7, 6, 38, 27, 7, 25, 30, 0, 0, 36, 37, 6, 39, 40, 32, 41, 12, 3, 44, 44, 39, 2, 26, 40, 36, 35, 21, 14, 41, 48, 50, 21, 0, 0, 23, 49, 21, 11, 27, 40, 47, 49}}
        };
    }
}