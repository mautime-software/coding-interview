package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/single-element-in-a-sorted-array
 *
 * You are given a sorted array of integers, nums, where all integers appear twice except for one.
 * Your task is to find and return the single integer that appears only once.
 * The solution should have a time complexity of O(logn) or better and a space complexity of O(1) .
 */
public class SingleElementInSortedArray {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findSingleElement((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int findSingleElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    private static int search(int[] nums, int i, int j) {
        if (i == j) {
            return nums[i];
        }
        if (i > j) {
            return -1;
        }
        int mid = (i + j) / 2;
        if (mid == 0) {
            return nums[mid] != nums[mid + 1] ? nums[mid] : -1;
        }
        if (mid == nums.length - 1) {
            return nums[mid] != nums[mid - 1] ? nums[mid] : -1;
        }
        if (mid % 2 != 0) { //if is odd
            if (nums[mid - 1] == nums[mid]) {
                return search(nums, mid + 1, j);
            }
            return search(nums, i, mid);
        } else {
            if (nums[mid] == nums[mid + 1]) {
                return search(nums, mid + 1, j);
            }
            return search(nums, i, mid);
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][]{
                {new int[]{1, 2, 2, 3, 3, 4, 4}},
                {new int[]{1, 1, 2, 2, 3, 4, 4, 5, 5}},
                {new int[]{1, 1, 2, 3, 3}},
                {new int[]{1, 1, 2}},
                {new int[]{0, 2, 2, 3, 3, 4, 4, 5, 5}}
        };
    }
}