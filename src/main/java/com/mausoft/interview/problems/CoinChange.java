package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/coin-change
 *
 * You're given an integer total and a list of integers called coins. The variable coins hold a list of coin denominations, and total is the total amount of money.
 *
 * You have to find the minimum number of coins that can make up the total amount by using any combination of the coins.
 * If the amount can't be made up, return -1. If the total amount is 0, return 0.
 */
public class CoinChange {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> coinChange((int[]) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int coinChange(int[] coins, int amount) {
        //return minCoinsRecursive(coins, amount, 0);
        return minCoinsRecursiveDP(coins, amount, 0, new int[amount + 1]);
        //return minCoinsForLoop(coins, amount);
    }

    private static int minCoinsRecursive(int[] coins, int amount, int i) {
        if (i == coins.length) {
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        if (coins[i] <= amount) {
            int c1 = minCoinsRecursive(coins, amount - coins[i], i);
            if (c1 != -1) {
                min = Math.min(c1 + 1, min);
            }
        }
        int c2 = minCoinsRecursive(coins, amount, i + 1);
        if (c2 != -1) {
            min = Math.min(c2, min);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static int minCoinsRecursiveDP(int[] coins, int amount, int i, int[] dp) {
        if (i == coins.length) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp[amount] != 0) {
            return dp[amount];
        }
        int minCoins = Integer.MAX_VALUE;
        if (coins[i] <= amount) {
            int c1 = minCoinsRecursiveDP(coins, amount - coins[i], i, dp);
            if (c1 != -1) {
                minCoins = Math.min(c1 + 1, minCoins);
            }
        }
        int c2 = minCoinsRecursiveDP(coins, amount, i + 1, dp);
        if (c2 != -1) {
            minCoins = Math.min(c2, minCoins);
        }
        dp[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return dp[amount];
    }

    private static int minCoinsForLoop(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int c1 = minCoinsForLoop(coins, amount - coin);
                if (c1 != -1) {
                    minCoins = Math.min(c1 + 1, minCoins);
                }
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 3, 4, 5}, 7},
                {new int[]{1,2,5}, 11},
                {new int[]{2, 3, 4, 5}, 7},
                {new int[]{2, 3, 4, 5}, 11},
                {new int[]{6, 7, 8}, 27},
                {new int[]{1, 2, 3, 4, 5}, 41},
                {new int[]{14, 15, 18, 20}, 52}
        };
    }
}