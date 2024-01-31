package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/search-in-rotated-sorted-array
 *
 * Given a sorted integer array, nums, and an integer value, target, the array is rotated by some arbitrary number.
 * Search and return the index of target in this array. If the target does not exist, return -1
 */
public class SearchRotatedSortedArray {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> search((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private static int binarySearch(int[] nums, int target, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) / 2;
        if (target == nums[mid]) {
            return mid;
        }
        if (nums[mid] < nums[j]) {
            if (target >= nums[mid] && target <= nums[j]) {
                return binarySearch(nums, target, mid + 1, j);
            }
            return binarySearch(nums, target, i, mid - 1);
        } else {
            if (target >= nums[i] && target <= nums[mid]) {
                return binarySearch(nums, target, i, mid - 1);
            }
            return binarySearch(nums, target, mid + 1, j);
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{4}, 1},
                {new int[]{1, 2, 3, 4, 5, 6, 7}, 1},
                {new int[]{10, 20, 30, 40, 50, 60}, 50},
                {new int[]{12, 24, 35, 47, 58, 69, 72, 83, 94}, 12},
                {new int[]{5, 13, 28, 41, 56, 63, 77, 82, 99, 105}, 56},
                {new int[]{5, 7, 12, 17, 21, 28, 33, 37, 41, 48, 52, 57, 62, 68, 72}, 5}
        };
    }
}