package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/counting-subarrays/
 *
 * Given an array A of N non-negative numbers and you are also given non-negative number B.
 *
 * You need to find the number of subarrays in A having sum less than B. We may assume that there is no overflow.
 *
 * #uber
 */
public class CountingSubarrays {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> countSliding((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int countSliding(int[] nums, int target) { //O(n) - Sliding Window
        int i = 0;
        int j = 0;
        int sum = 0;
        int result = 0;
        while (j < nums.length) {
            sum += nums[j];
            while (sum >= target && i <= j) {
                if (nums[i] < target) {
                    result += (j - i);
                }
                sum -= nums[i];
                i++;
            }
            j++;
        }
        while (i < nums.length) {
            if (sum < target) {
                result += (j - i);
            }
            sum -= nums[i];
            i++;
        }
        return result;
    }

    public static int count(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += count(nums, i, target);
        }
        return count;
    }

    private static int count(int[] nums, int i, int target) {
        if (i == nums.length) {
            return 0;
        }
        if (nums[i] >= target) {
            return 0;
        }
        return 1 + count(nums, i + 1, target - nums[i]);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{2, 5, 6}, 10},
                {new int[]{1, 11, 2, 3, 15}, 10},
                {new int[]{8, 5, 1, 10, 5, 9, 9, 3, 5, 6, 6, 2, 8, 2, 2, 6, 3, 8, 7, 2, 5, 3, 4, 3, 3, 2, 7, 9, 6, 8, 7, 2, 9, 10, 3, 8, 10, 6, 5, 4, 2, 3, 4, 4, 5, 2, 2, 4, 9, 8, 5}, 32}
        };
    }
}