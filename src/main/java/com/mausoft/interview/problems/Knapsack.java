package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/0-1-knapsack
 *
 * You are given n items whose weights and values are known, as well as a knapsack to carry these items.
 * The knapsack cannot carry more than a certain maximum weight, known as its capacity.
 * You need to maximize the total value of the items in your knapsack, while ensuring that the sum of the weights of the selected items does not exceed the capacity of the knapsack.
 *
 * If there is no combination of weights whose sum is within the capacity constraint, return 0.
 */
public class Knapsack {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> knapsack((int[]) e[0], (int[]) e[1], (int) e[2]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int knapsack(int[] weight, int[] values, int capacity) {
        int[][] dp = new int[values.length + 1][capacity + 1];
        return maxProfitDynamicProgramming(weight, values, capacity, 0, dp);
    }

    public static int maxProfitRecursive(int[] weight, int[] values, int capacity, int i) {
        if (capacity < 0 || i == values.length) {
            return 0;
        }
        int profits = 0;
        if (weight[i] <= capacity) {
            profits = values[i] + maxProfitRecursive(weight, values, capacity - weight[i], i + 1);
        }
        int profits2 = maxProfitRecursive(weight, values, capacity, i + 1);
        return Math.max(profits, profits2);
    }

    public static int maxProfitDynamicProgramming(int[] weight, int[] values, int capacity, int i, int[][] dp) {
        if (capacity < 0 || i >= weight.length) {
            return 0;
        }

        if (dp[i][capacity] != 0) {
            return dp[i][capacity];
        }
        int profits = 0;
        if (weight[i] <= capacity) {
            profits += values[i] + maxProfitDynamicProgramming(weight, values, capacity - weight[i], i + 1, dp);
        }
        int profits1 = maxProfitDynamicProgramming(weight, values, capacity, i + 1, dp);
        dp[i][capacity] = Math.max(profits, profits1);
        return dp[i][capacity];
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 3, 5}, new int[]{1, 5, 4, 8}, 6},
                {new int[]{4}, new int[]{2}, 3},
                {new int[]{2}, new int[]{3}, 3},
                {new int[]{3, 6, 10, 7, 2}, new int[]{12, 10, 15, 17, 13}, 10},
                {new int[]{3, 6, 10, 7, 2, 12, 15, 10, 13, 20}, new int[]{12, 10, 15, 17, 13, 12, 30, 15, 18, 20}, 20}
        };
    }
}