package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Consumer;

import static com.mausoft.interview.common.util.ArrayUtils.swap;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/rotate-image
 *
 * Given an n×n matrix, rotate the matrix 90 degrees clockwise. The performed rotation should be in place,
 * i.e., the given matrix is modified directly without allocating another matrix.
 */
public class RotateImage {
    public static void main(String... args) {
        Consumer<Object[]> consumer = e -> rotate((int[][]) e[0]);
        TestExecutor.runTestCases(consumer, dataProvider());
    }

    public static void rotate(int[][] array) {
        for (int i = 0; i < array.length / 2; i++) {
            rotate(array, i);
        }
    }

    private static void rotate(int[][] array, int start) {
        int[] topLeft = {start, start};
        int[] topRight = {start, array[topLeft[0]].length - start - 1};
        int[] bottomLeft = {array.length - start - 1, start};
        int[] bottomRight = {bottomLeft[0], array[bottomLeft[0]].length - start - 1};

        while (topLeft[1] < topRight[1]) {
            swap(array, topLeft, bottomLeft);
            swap(array, bottomLeft, bottomRight);
            swap(array, bottomRight, topRight);
            topLeft[1]++;
            topRight[0]++;
            bottomLeft[0]--;
            bottomRight[1]--;
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                //{new int[][]{{1}}},
                {new int[][]{{6, 9}, {2, 7}}},
                {new int[][]{{2, 14, 8}, {12, 7, 14}, {3, 3, 7}}},
                {new int[][]{{3, 1, 1, 7}, {15, 12, 13, 13}, {4, 14, 12, 4}, {10, 5, 11, 12}}},
                {new int[][]{{10, 1, 14, 11, 14}, {13, 4, 8, 2, 13}, {10, 19, 1, 6, 8}, {20, 10, 8, 2, 12}, {15, 6, 8, 8, 18}}}
        };
    }
}