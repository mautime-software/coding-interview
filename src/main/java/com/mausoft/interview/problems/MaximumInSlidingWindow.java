package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/find-maximum-in-sliding-window
 *
 * Given an integer list, nums, find the maximum values in all the contiguous subarrays (windows) of size w.
 *
 * -- To find the maximum element in all possible contiguous subarrays of a given array, you can use the "Sliding Window Maximum" algorithm.
 *    This algorithm efficiently finds the maximum in a sliding window of a fixed size --
 *
 * Remove indexes out of the window
 * Remove items smaller than current
 * Add max number to results
 */
public class MaximumInSlidingWindow {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findMaxs((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int[] findMaxs(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> results = new ArrayList<>();
        k = k > nums.length ? nums.length : k;
        for (int i = 0; i < nums.length; i++) {
            //remove indexes outside of the window
            while (!deque.isEmpty() && deque.peek() < (i - k + 1)) {
                deque.poll();
            }
            //remove smaller numbers than current
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            //add current index
            deque.offer(i);
            if (i >= k - 1) {
                results.add(nums[deque.peek()]);
            }
        }
        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 3},
                {new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 3},
                {new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 3},
                {new int[]{1, 5, 8, 10, 10, 10, 12, 14, 15, 19, 19, 19, 17, 14, 13, 12, 12, 12, 14, 18, 22, 26, 26, 26, 28, 29, 30}, 3},
                {new int[]{10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67}, 2},
                {new int[]{4, 5, 6, 1, 2, 3}, 4},
                {new int[]{9, 5, 3, 1, 6, 3}, 3},
                {new int[]{2, 4, 6, 8, 10, 12, 14, 16}, 2},
                {new int[]{-1, -1, -2, -4, -6, -7}, 3},
                {new int[]{4, 4, 4, 4, 4, 4}, 18}
        };
    }
}