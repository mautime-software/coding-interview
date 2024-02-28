package com.mausoft.interview.problems.leetcode;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * Example 2:
 *
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * Example 3:
 *
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 *
 */
public class MergeSortedArrays {
    public static void main(String... args) {
        Consumer<Object[]> function = e -> merge((int[]) e[0], (int) e[1], (int[]) e[2], (int) e[3]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] results = new int[m + n];
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                results[k++] = nums1[i];
                i++;
            } else {
                results[k++] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            results[k++] = nums1[i++];
        }
        while (j < n) {
            results[k++] = nums2[j++];
        }
        for (int l = 0; l < results.length; l++) {
            nums1[l] = results[l];
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3},
                {new int[]{1}, 1, new int[0], 0},
                {new int[]{0}, 0, new int[]{1}, 1}
        };
    }
}