package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/sum-of-three-values
 *
 * Given an array of integers, nums, and an integer value, target, determine if there are any three integers in nums whose sum is equal to the target,
 * that is, nums[i] + nums[j] + nums[k] == target. Return TRUE if three such integers exist in the array. Otherwise, return FALSE.
 */
public class ThreeSum {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isThreeSumPossible((int[]) e[0], (Integer) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    private static Object[][] dataProvider() {
        return new Object[][]{
                {new int[]{3, 7, 1, 2, 8, 4, 5}, 10},
                {new int[]{-1, 2, 1, -4, 5, -3}, 7},
                {new int[]{2, 3, 4, 1, 7, 9}, 20},
                {new int[]{1, -1, 0}, -1},
                {new int[]{2, 4, 2, 7, 6, 3, 1}, 8}
        };
    }
    public static boolean isThreeSumPossible(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (isTwoSumPossible(nums, i + 1, diff)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTwoSumPossible(int[] nums, int start, int target) {
        Map<Integer, Integer> diffMap = new HashMap<>();
        for (int i = start; i < nums.length; i++) {
            int diff = target - nums[i];
            if (diffMap.containsKey(diff)) {
                return true;
            }
            diffMap.put(nums[i], i);
        }
        return false;
    }
}