package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/binary-search
 *
 * We are given an array of integers, nums, sorted in ascending order, and an integer value, target.
 * If the target exists in the array, return its index. If the target does not exist, return -1.
 *
 * mid = (i + j) / 2
 */
public class BinarySearch {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> find((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int find(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private static int binarySearch(int[] nums, int i, int j, int target) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (target < nums[mid]) {
            return binarySearch(nums, i, mid - 1, target);
        }
        return binarySearch(nums, mid + 1, j, target);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,6,8,10}, 1},
                {new int[]{11,22,33,44,55,66,77}, 33},
                {new int[]{-3,-1,0,11,15}, 0},
                {new int[]{-30,-27,-8,-6,-1}, -1},
                {new int[]{0}, 0},
                {new int[]{}, 12},
                {new int[]{-1, 0, 3, 5, 9, 12}, 9},
                {new int[]{-100, -67, -55, -50, -49, -40, -33, -22, -10, -5}, -22}
        };
    }
}