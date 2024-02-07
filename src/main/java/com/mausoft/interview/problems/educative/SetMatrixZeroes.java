package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SetMatrixZeroes {
    public static void main(String... args) {
        Consumer<Object[]> consumer = e -> setZeroes((int[][]) e[0]);
        TestExecutor.runTestCases(consumer, dataProvider());
    }

    public static void setZeroes(int[][] array) {
        int[][] zeroes = findZeroes(array);
        markZeroes(array, zeroes);
    }

    private static int[][] findZeroes(int[][] array) {
        List<int[]> zeroes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    zeroes.add(new int[]{i, j});
                }
            }
        }
        return zeroes.toArray(new int[zeroes.size()][]);
    }

    private static void markZeroes(int[][] array, int[][] zeroes) {
        for (int[] zero : zeroes) {
            for (int i = 0; i < array.length; i++) {
                array[i][zero[1]] = 0;
            }
            for (int i = 0; i < array[0].length; i++) {
                array[zero[0]][i] = 0;
            }
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{1, 1, 0}, {1, 0, 1}, {1, 1, 1}}},
                {new int[][]{{1, 1, 1, 1, 1}, {0, 0, 1, 1, 1}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}}},
                {new int[][]{{3, 5, 2, 0}, {1, 0, 4, 6}, {7, 3, 2, 4}}},
                {new int[][]{{1, 2, 3, 4}, {4, 5, 6, 7}, {8, 9, 4, 6}}},
                {new int[][]{{2, 6, 5, 4, 9, 1}, {7, 2, 0, 0, 5, 4}, {1, 1, 1, 1, 0, 1}, {9, 8, 2, 0, 1, 3}, {7, 8, 6, 5, 4, 3}, {9, 8, 1, 2, 5, 6}}}
        };
    }
}