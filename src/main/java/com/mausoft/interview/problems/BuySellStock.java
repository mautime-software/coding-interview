package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/best-time-to-buy-and-sell-stock
 *
 * Given an array where the element at the index i represents the price of a stock on day i,
 * find the maximum profit that you can gain by buying the stock once and then selling it.
 *
 */
public class BuySellStock {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> maxProfit((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int maxProfit(int[] stocks) {
        int i = 0;
        int j = 1;
        int max = 0;
        while (j < stocks.length) {
            int profit = stocks[j] - stocks[i];
            if (profit < 0) {
                i = j;
            }
            max = Math.max(profit, max);
            j++;
        }
        return max;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9}},
                {new int[]{7, 1, 5, 3, 6, 4}},
                {new int[]{7, 6, 4, 3, 1}},
                {new int[]{2, 6, 8, 7, 8, 7, 9, 4, 1, 2, 4, 5, 8}},
                {new int[]{1, 4, 2}}
        };
    }
}