package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/solution-jump-game-i
 *
 * In a single-player jump game, the player starts at one end of a series of squares, with the goal of reaching the last square.
 * At each turn, the player can take up to s steps towards the last square, where s is the value of the current square.
 *
 * For example, if the value of the current square is 3, the player can take either 3 steps, or2 steps, or 1
 * step in the direction of the last square. The player cannot move in the opposite direction, that is, away from the last square.
 *
 * You have been tasked with writing a function to validate whether a player can win a given game or not.
 *
 * You’ve been provided with the nums integer array, representing the series of squares.
 * The player starts at the first index and, following the rules of the game, tries to reach the last index.
 *
 * If the player can reach the last index, your function returns TRUE; otherwise, it returns FALSE.
 */
public class JumpGameI {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> play((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static boolean play(int[] nums) {
        int tIdx = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (tIdx <= (i + nums[i])) {
                tIdx = i;
            }
        }
        return tIdx == 0;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{3, 2, 2, 0, 1, 4}},
                {new int[]{2, 3, 1, 1, 9}},
                {new int[]{3, 2, 1, 0, 4}},
                {new int[]{0}},
                {new int[]{1}},
                {new int[]{4, 3, 2, 1, 0}},
                {new int[]{1, 1, 1, 1, 1}},
                {new int[]{4, 0, 0, 0, 1}},
                {new int[]{3, 3, 3, 3, 3}},
                {new int[]{1, 2, 3, 4, 5, 6, 7}}
        };
    }
}