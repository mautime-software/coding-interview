package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/kth-largest-element-in-a-stream
 *
 * Given an infinite stream of integers (sorted or unsorted), nums, design a class to find the kth largest element in a stream.
 */
public class KthLargestElementInStream {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findKthMax((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int findKthMax(int[] nums, int k) { //n log k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        return minHeap.peek();
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{3, 6, 9, 12}, 3},
                {new int[]{3, 6, 9, 10, 4, 7}, 3},
                {new int[]{3, 6, 9, 10, 4, 7, 10}, 3},
                {new int[]{3, 6, 9, 10, 4, 7, 10, 8}, 3},
                {new int[]{3, 6, 9, 10, 4, 7, 10, 8, 15}, 3}
        };
    }
}