package com.mausoft.interview.problems.leetcode;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.mausoft.interview.common.util.ArrayUtils.swap;

/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 *
 * Example 2:
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 */
public class WiggleSortII {
    public static void main(String... args) {
        Consumer<Object[]> consumer = e -> wiggle((int[]) e[0]);
        TestExecutor.runTestCases(consumer, dataProvider());
    }

    public static void wiggle(int[] nums) {
        Arrays.sort(nums);
        int[] auxArray = new int[nums.length];
        int mid = nums.length / 2;
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            auxArray[i] = i % 2 == 0 ? nums[mid--] : nums[j--];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = auxArray[i];
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,5,1,1,6,4}},
                {new int[]{1,3,2,2,3,1}},
                {new int[]{1,4,3,4,1,2,1,3,1,3,2,3,3}}
        };
    }
}