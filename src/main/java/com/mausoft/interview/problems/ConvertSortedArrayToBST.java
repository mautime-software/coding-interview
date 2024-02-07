package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/convert-sorted-array-to-binary-search-tree
 *
 * Given an array of integers, nums, sorted in ascending order, your task is to construct a height-balanced binary search tree (BST) from this array.
 *
 * In a height-balanced BST, the difference of heights of the left subtree and right subtree of any node is not more than 1.
 */
public class ConvertSortedArrayToBST {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> convert((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static TreeNode<Integer> convert(int[] nums) {
        return convert(nums, 0, nums.length - 1);
    }

    private static TreeNode<Integer> convert(int[] nums, int i, int j) {
        if (i < 0 || j == nums.length || i > j) {
            return null;
        }
        // int mid = i + (j - i) / 2; another way to calculate middle
        int mid = (j + i) / 2;
        TreeNode<Integer> leftNode = convert(nums, i, mid - 1);
        TreeNode<Integer> rightNode = convert(nums, mid + 1, j);
        return new TreeNode<>(nums[mid], leftNode, rightNode);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2}},
                {new int[]{1, 2, 3}},
                {new int[]{50, 100, 150}},
                {new int[]{1, 2, 3, 4}},
                {new int[]{11, 22, 33, 44, 55, 66, 77, 88}},
                {new int[]{25, 50, 75, 100, 125, 350}},
                {new int[]{-10, -3, 0, 5, 9}}
        };
    }
}