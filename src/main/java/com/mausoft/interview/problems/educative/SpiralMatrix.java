package com.mausoft.interview.problems.educative;


import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/spiral-matrix
 *
 * Given an m×n matrix, return an array containing the matrix elements in spiral order, starting from the top-left cell.
 */
public class SpiralMatrix {
    public static void main(String... args) {
        System.out.println("====================================== SOLUTION ======================================");
        Function<Object[], Object> function = e -> spiralMatrix((int[][]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
        System.out.println("====================================== ALTERNATIVE SOLUTION ======================================");
        Function<Object[], Object> alternativeFunction = e -> spiralMatrixAlternative((int[][]) e[0]);
        TestExecutor.runTestCases(alternativeFunction, dataProvider());
    }

    public static int[] spiralMatrix(int[][] array) {
        int x = 0;
        int i = 0;
        int[] result = new int[array.length * array[0].length];
        while (x < result.length) {
            for (int j = i; j < array[i].length - i; j++) { //spiral top
                result[x++] = array[i][j];
            }
            for (int j = i + 1; j < array.length - i; j++) { //spiral right
                result[x++] = array[j][array[i].length - i - 1];
            }
            for (int j = array[i].length - i - 2; j >= i; j--) { //spiral bottom
                result[x++] = array[array.length - i - 1][j];
            }
            for (int j = array.length - i - 2; j > i; j--) { //spiral left
                result[x++] = array[j][i];
            }
            i++;
        }
        return result;
    }

    public static int[] spiralMatrixAlternative(int[][] matrix) {
        int j = 0;
        int row = 0;
        int col = -1;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int direction = 1;
        int[] results = new int[rows * cols];
        while (rows > 0 && cols > 0) {
            for (int i = 0; i < cols; i++) {
                col += direction;
                results[j++] = matrix[row][col];
            }
            rows--;
            for (int i = 0; i < rows; i++) {
                row += direction;
                results[j++] = matrix[row][col];
            }
            cols--;
            direction *= -1;
        }
        return results;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{1}}},
                {new int[][]{{6}, {2}}},
                {new int[][]{{2, 14, 8}, {12, 7, 14}}},
                {new int[][]{{3, 1, 1}, {15, 12, 13}, {4, 14, 12}, {10, 5, 11}}},
                {new int[][]{{10, 1, 14, 11, 14}, {13, 4, 8, 2, 13}, {10, 19, 1, 6, 8}, {20, 10, 8, 2, 12}, {15, 6, 8, 8, 18}}}
        };
    }
}