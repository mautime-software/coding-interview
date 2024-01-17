package com.mausoft.interview.core.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.mausoft.interview.common.util.ArrayUtils.copy;

/**
 * https://www.programiz.com/dsa/merge-sort
 *
 * Best	O(n*log n)
 * Worst	O(n*log n)
 * Average	O(n*log n)
 * Space Complexity	O(n)
 * Stable	Yes
 */
public class MergeSort {

    public static void main(String... args) {
        int[] nums = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        mergeSort(nums, 0, nums.length - 1);
        String result = Arrays.stream(nums).mapToObj(Integer::toString).collect(Collectors.joining(", "));
        System.out.println(result);
    }

    public static void mergeSort(int[] nums, int i, int j) {
        if (i >= j) {
            return;
        }
        int mid = (i + j) / 2;
        mergeSort(nums, i, mid);
        mergeSort(nums, mid + 1, j);
        merge(nums, i, mid, j);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] leftArr = copy(nums, start, mid);
        int[] rightArr = copy(nums, mid + 1, end);
        int i = 0;
        int j = 0;
        int k = start;
        while(i < leftArr.length && j < rightArr.length) {
            nums[k] = leftArr[i] <= rightArr[j] ? leftArr[i++] : rightArr[j++];
            k++;
        }
        while(i < leftArr.length) {
            nums[k] = leftArr[i++];
            k++;
        }
        while(j < rightArr.length) {
            nums[k] = rightArr[j++];
            k++;
        }
    }
}