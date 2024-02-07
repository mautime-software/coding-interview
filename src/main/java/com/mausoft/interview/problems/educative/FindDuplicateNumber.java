package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-find-the-duplicate-number
 *
 * Given an unsorted array of positive numbers, nums, such that the values lie in the range
 * [1, n], inclusive, and that there are n + 1 numbers in the array, find and return the duplicate number present in nums.
 *
 * There is only one repeated number in nums.
 *
 * //Find where both pointers meet
 * //Iterate one by one (slow = start, fast = fast)
 */
public class FindDuplicateNumber {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findDuplicate((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 3, 2, 3, 5, 4}},
                {new int[]{2, 4, 5, 4, 1, 3}},
                {new int[]{1, 6, 3, 5, 1, 2, 7, 4}},
                {new int[]{1, 2, 2, 4, 3}},
                {new int[]{3, 1, 3, 5, 6, 4, 2}}
        };
    }
}