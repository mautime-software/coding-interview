package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/word-search
 *
 * Given an m×n 2D grid of characters and word as a string, we need to determine if the word can be constructed from letters of sequentially adjacent cells.
 * The cells are considered sequentially adjacent when they are neighbors to each other either horizontally or vertically.
 * The function should return TRUE if the word can be constructed and FALSE otherwise.
 */
public class WordSearch {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> exists((char[][]) e[0], (String) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static boolean exists(char[][] matrix, String word) {
        return exists(matrix, 0, 0, word, 0, new boolean[matrix.length][matrix[0].length]);
    }

    private static boolean exists(char[][] matrix, int x, int y, String word, int i, boolean[][] visited) {
        if (x < 0 || y < 0 || x == matrix.length || y == matrix[0].length) {
            return false;
        }
        if (i == word.length()) {
            return true;
        }
        if (visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        boolean exists = exists(matrix, x - 1, y, word, i + 1, visited) ||
                exists(matrix, x, y + 1, word, i + 1, visited) ||
                exists(matrix, x + 1, y, word, i + 1, visited) ||
                exists(matrix, x, y - 1, word, i  + 1, visited);
        visited[x][y] = false;
        return exists && matrix[x][y] == word.charAt(i);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new char[][]{{'E', 'D', 'X', 'I', 'W'},
                        {'P', 'U', 'F', 'M', 'Q'},
                        {'I', 'C', 'Q', 'R', 'F'},
                        {'M', 'A', 'L', 'C', 'A'},
                        {'J', 'T', 'I', 'V', 'E'}}, "EDUCATIVE"},
                {new char[][]{{'O', 'Y', 'O', 'I'},
                        {'B', 'I', 'E', 'M'},
                        {'K', 'D', 'Y', 'R'},
                        {'M', 'T', 'W', 'I'},
                        {'Z', 'I', 'T', 'O'}}, "DYNAMIC"},
                {new char[][] {{'h', 'e', 'c', 'm', 'l'},
                        {'w', 'l', 'i', 'e', 'u'},
                        {'a', 'r', 'r', 's', 'n'},
                        {'s', 'i', 'i', 'o', 'r'}}, "WARRIOR"},
                {new char[][] {{'C', 'Q', 'N', 'A'},
                        {'P', 'S', 'E', 'I'},
                        {'Z', 'A', 'P', 'E'},
                        {'J', 'V', 'T', 'K'}}, "SAVE"},
                {new char[][] {{'A'}}, "ABC"},
                {new char[][] {{'P', 'S', 'S', 'I', 'W', 'P'},
                        {'P', 'Y', 'C', 'A', 'Q', 'T'},
                        {'I', 'S', 'H', 'P', 'F', 'Y'},
                        {'M', 'T', 'O', 'L', 'O', 'I'},
                        {'J', 'I', 'N', 'O', 'G', 'K'},
                        {'I', 'M', 'D', 'T', 'Y', 'T'}}, "PSYCHOLOGY"}
        };
    }
}