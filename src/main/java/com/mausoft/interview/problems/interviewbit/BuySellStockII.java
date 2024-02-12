package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/best-time-to-buy-and-sell-stocks-ii/
 *
 * Say you have an array, A, for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BuySellStockII {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> maxProfit((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int maxProfit(int[] stocks) {
        if (stocks == null || stocks.length == 0) {
            return 0;
        }
        int i = 0;
        int j = 1;
        int profit = 0;
        while (j < stocks.length) {
            if (stocks[i] < stocks[j]) {
                profit += stocks[j] - stocks[i];
                i++;
            } else {
                i = j;
            }
            j++;
        }
        return profit;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 3}},
                {new int[]{5, 2, 10}}
        };
    }
}