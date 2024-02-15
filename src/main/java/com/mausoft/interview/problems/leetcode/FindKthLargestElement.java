package com.mausoft.interview.problems.leetcode;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.PriorityQueue;
import java.util.function.Function;

import static com.mausoft.interview.common.util.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 */
public class FindKthLargestElement {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findKthLargestHeap((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int findKthLargestHeap(int[] nums, int k) { // O(n log n)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (minHeap.isEmpty() || minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        return minHeap.peek();
    }

    public static int findKthLargest(int[] nums, int k) { // O(n log n) --average
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int pivot = partition(nums, i, j);
             if (pivot == k - 1) {
                 return nums[pivot];
             }
             if (pivot < k - 1) {
                 i = pivot + 1;
             } else {
                 j = pivot - 1;
             }
        }
        return -1;
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;
        for (int j = i; j < end; j++) {
            if (nums[j] > pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{3,2,1,5,6,4}, 2},
                {new int[]{3,2,3,1,2,4,5,5,6}, 4}
        };
    }
}