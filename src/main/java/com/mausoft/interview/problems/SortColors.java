package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Consumer;

import static com.mausoft.interview.common.util.ArrayUtils.swap;
/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/sort-colors
 *
 * Given an array, colors, which contains a combination of the following three elements:
 *
 * 0 (representing red)
 * 1 (representing white)
 * 2 (representing blue)
 *
 * Sort the array in place so that the elements of the same color are adjacent, with the colors in the order of red, white, and blue.
 * The function should return the same array.
 */
public class SortColors {
    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int BLUE = 2;
    public static void main(String... args) {
        Consumer<Object[]> consumer = e -> sortColors((int[]) e[0]);
        TestExecutor.runTestCases(consumer, dataProvider());
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{0,1,0}},
                {new int[]{1}},
                {new int[]{2,2}},
                {new int[]{1,1,0,2}},
                {new int[]{2,1,1,0,0}}

        };
    }
    private static void sortColors(int[] colors) {
        int r = 0;
        int i = 0;
        int j = colors.length - 1;
        while (i <= j) {
            if (colors[i] == RED) {
                if (colors[r] != RED) {
                    swap(colors, i, r);
                }
                r++;
                i++;
            } else if (colors[i] == WHITE) {
                i++;
            } else if (colors[i] == BLUE) {
                if (colors[j] != BLUE) {
                    swap(colors, i, j);
                }
                j--;
            }
        }
    }
}