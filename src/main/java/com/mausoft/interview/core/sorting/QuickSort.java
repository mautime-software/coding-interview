package com.mausoft.interview.core.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.mausoft.interview.common.util.ArrayUtils.swap;

/**
 * https://www.programiz.com/dsa/quick-sort
 *
 * Best	O(n*log n)
 * Worst	O(n2)
 * Average	O(n*log n)
 *
 * Space Complexity	O(log n)
 * Stable   No
 */
public class QuickSort {
    public static void main(String... args) {
        int[] nums = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        quicksort(nums, 0, nums.length - 1);
        String result = Arrays.stream(nums).mapToObj(Integer::toString).collect(Collectors.joining(", "));
        System.out.println(result);
    }
    public static void quicksort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int p = partition(nums, start, end);
        quicksort(nums, start, p - 1);
        quicksort(nums, p + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }
}