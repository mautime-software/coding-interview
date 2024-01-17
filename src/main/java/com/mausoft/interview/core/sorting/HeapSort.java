package com.mausoft.interview.core.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.mausoft.interview.common.util.ArrayUtils.swap;

/**
 * https://www.programiz.com/dsa/heap-sort
 *
 * - A heap is a complete binary tree (all leaf elements must lean towards the left, the last leaf element might not have a right sibling)
 * - All nodes are greater than their children (max-heap)
 * - All nodes are smaller than their children (min-heap)
 *
 * left -> 2i+1
 * right -> 2i+2
 * parent -> (i - 1) / 2
 * last index of non-leaf node -> n / 2 - 1
 *
 * swap/remove/heapify
 *
 * Best	O(n*log n)
 * Worst	O(n*log n)
 * Average	O(n*log n)
 * Space Complexity	O(n)
 * Stable	Yes
 */
public class HeapSort {
    public static void main(String... args) {
        int[] nums = { 1, 12, 9, 5, 6, 10 };
        heapSort(nums);
        String results = Arrays.stream(nums).mapToObj(Integer::toString).collect(Collectors.joining(", "));
        System.out.println(results);
    }

    public static void heapSort(int[] nums) {
        buildMaxHeap(nums);
        sortHeap(nums);
    }

    private static void buildMaxHeap(int[] nums) {
        for (int i = (nums.length / 2) - 1; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
    }

    private static void sortHeap(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i); //swap
            heapify(nums, 0, i); //remove (already removed), heapify
        }
    }

    private static void heapify(int[] nums, int i, int n) {
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        int max = i;
        if (left < n && nums[left] < nums[max]) {
            max = left;
        }
        if (right < n && nums[right] < nums[max]) {
            max = right;
        }
        if (max != i) {
            swap(nums, i, max);
            heapify(nums, max, n);
        }
    }
}
