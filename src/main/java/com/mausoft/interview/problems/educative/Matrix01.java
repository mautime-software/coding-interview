package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/01-matrix
 *
 * Given an m×n binary matrix, mat, find the distance from each cell to the nearest 0. The distance between two adjacent cells is 1.
 *
 * Cells to the left, right, above, and below the current cell will be considered adjacent.
 */
//TODO implement O(mxn) solution
public class Matrix01 {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> minDistance((int[][])e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int[][] minDistance(int[][] matrix) {
        int[][] distances = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                explore(matrix, i, j, distances, new boolean[matrix.length][matrix[0].length]);
            }
        }
        return distances;
    }

    private static int explore(int[][] matrix, int x, int y, int[][] distances, boolean[][] visited) {
        if ((x < 0 || y < 0) || (x == matrix.length || y == matrix[0].length)) {
            return -1;
        }
        if (visited[x][y]) {
            return -1;
        }
        if (distances[x][y] != 0) {
            return distances[x][y];
        }
        if (matrix[x][y] == 0) {
            return 0;
        }
        visited[x][y] = true;
        int min = Integer.MAX_VALUE;
        int d1 = explore(matrix, x - 1, y, distances, visited);
        if (d1 != -1) {
            min = Math.min(d1, min);
        }
        int d2 = explore(matrix, x, y + 1, distances, visited);
        if (d2 != -1) {
            min = Math.min(d2, min);
        }
        int d3 = explore(matrix, x + 1, y, distances, visited);
        if (d3 != -1) {
            min = Math.min(d3, min);
        }
        int d4 = explore(matrix, x, y - 1, distances, visited);
        if (d4 != -1) {
            min = Math.min(d4, min);
        }
        visited[x][y] = false;
        distances[x][y] = min != Integer.MAX_VALUE ? min + 1 : -1;
        return distances[x][y];
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}, {1, 1, 1, 1}}},
                {new int[][]{{0, 1}, {1, 1}}},
                {new int[][]{{0, 0, 1}, {0, 1, 1}, {1, 0, 1}}},
                {new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 0, 1}}},
                {new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}},
                {new int[][]{{0, 1, 0, 1}, {1, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}}}
        };
    }
}