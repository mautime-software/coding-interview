package com.mausoft.interview.common.util;

public class ArrayUtils {
    public static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void swap(int[][] nums, int[] a, int[] b) {
        int tmp = nums[a[0]][a[1]];
        nums[a[0]][a[1]] = nums[b[0]][b[1]];
        nums[b[0]][b[1]] = tmp;
    }

    public static int[] copy(int[] nums, int i, int j) {
        int[] copyArr = new int[j - i + 1];
        for (int x = 0; x < copyArr.length; x++) {
            copyArr[x] = nums[i + x];
        }
        return copyArr;
    }
}