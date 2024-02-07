package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/find-k-closest-elements
 *
 * You are given a sorted array of integers, nums, and two integers, target and k. Your task is to return k number of integers that are close to the target value, target. The integers in the output array should be in a sorted order.
 *
 * An integer, nums[i], is considered to be closer to target, as compared to nums[j] when |nums[i] - target| < |nums[j] - target|.
 * However, when |nums[i] - target| = |nums[j] - target|, the smaller of the two values is selected.
 */
public class FindKClosestElements {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findClosestElements((int[]) e[0], (int) e[1], (int) e[2]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static List<Integer> findClosestElements(int[] nums, int target, int k) {
        if (nums.length == k) {
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        }
        if (target <= nums[0]) {
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                results.add(nums[i]);
            }
            return results;
        }

        if (target >= nums[nums.length - 1]) {
            List<Integer> results = new ArrayList<>();
            for (int i = nums.length - k; i < nums.length; i++) {
                results.add(nums[i]);
            }
            return results;
        }
        int firstClosest = findFirstClosest(nums, target);
        int i = firstClosest - 1;
        int j = i + 1;
        //sliding window
        while ((j - i - 1) < k) {
            if (i == -1) {
                j++;
            } else {
                if (j == nums.length || Math.abs(nums[i] - target) <= Math.abs(nums[j] - target)) {
                    i--;
                } else {
                    j++;
                }
            }
        }
        List<Integer> results = new ArrayList<>();
        for (int m = i + 1; m < j; m++) {
            results.add(nums[m]);
        }
        return results;
    }

    private static int findFirstClosest(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private static int search(int[] nums, int target, int i, int j) {
        if (i == j) {
            return i;
        }
        int mid = (i + j) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (target < nums[mid]) {
            return search(nums, target, i, mid - 1);
        } else {
            return search(nums, target, mid + 1, j);
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 3, 4, 5, 6, 7}, 4, 4},
                {new int[]{1, 2, 3, 4, 5}, 3, 4},
                {new int[]{1, 2, 4, 5, 6}, 10, 2},
                {new int[]{1, 2, 3, 4, 5, 10}, -5, 3}
        };
    }
}