package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 *
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/circular-array-loop
 *
 * An input array, nums containing non-zero integers, is given, where the value at each index represents the number of places to skip forward
 * (if the value is positive) or backward (if the value is negative). When skipping forward or backward, wrap around if you reach either end of the array.
 * For this reason, we are calling it a circular array. Determine if this circular array has a cycle.
 *
 * A cycle is a sequence of indices in the circular array characterized by the following:
 *
 *  - The same set of indices is repeated when the sequence is traversed in accordance with the aforementioned rules.
 *  - The length of the sequence is at least two.
 *  - The loop must be in a single direction, forward or backward.
 *  - It should be noted that a cycle in the array does not have to originate at the beginning. A cycle can begin from any point in the array.
 *
 */
public class CircularArrayLoop {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isCircular((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static boolean isCircular(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int slow = i;
            int fast = i;
            boolean direction = isForward(nums, i);
            while(true) {
                fast = getNextIndex(nums, fast);
                if (isDifferentDirection(nums, fast, direction) || isSingleElement(nums, fast)) {
                    break;
                }
                fast = getNextIndex(nums, fast);
                if (isDifferentDirection(nums, fast, direction) || isSingleElement(nums, fast)) {
                    break;
                }
                slow = getNextIndex(nums, slow);
                if (isDifferentDirection(nums, slow, direction) || isSingleElement(nums, slow)) {
                    break;
                }
                if (slow == fast) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    private static int getNextIndex(int[] nums, int i) {
        int next = (nums[i] + i) % nums.length; //next index
        return next >= 0 ? next : next + nums.length;
    }

    private static boolean isDifferentDirection(int[] nums, int i, boolean forward) {
        return isForward(nums, i) != forward;
    }

    private static boolean isForward(int[] nums, int i) {
        return nums[i] >= 0;
    }

    private static boolean isSingleElement(int[] nums, int i) {
        return getNextIndex(nums, i) == i;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 1, 1}},
                {new int[]{-5, -4, -3, -2, -1}},
                {new int[]{-1, -2, -3, -4, -5}},
                {new int[]{2, 1, -1, -2}},
                {new int[]{-1, -2, -3, -4, -5, 6}},
                {new int[]{1, 2, -3, 3, 4, 7, 1}},
                {new int[]{2, 2, 2, 7, 2, -1, 2, -1, -1}}
        };
    }
}