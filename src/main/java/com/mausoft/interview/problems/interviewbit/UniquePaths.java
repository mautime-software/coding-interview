package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/unique-paths-in-a-grid/
 *
 * Given a grid of size m * n, lets assume you are starting at (1,1) and your goal is to reach (m,n). At any instance,
 * if you are on (x,y), you can either go to (x, y + 1) or (x + 1, y).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 */
public class UniquePaths {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> uniquePaths((int[][])e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int uniquePaths(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return explore(grid, 0, 0, dp);
    }

    private static int explore(int[][] grid, int x, int y, int[][] dp) {
        if (x == grid.length || y == grid[0].length) {
            return 0;
        }
        if (dp[x][y] > 0) {
            return dp[x][y];
        }
        if (grid[x][y] == 1) {
            return 0;
        }
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return 1;
        }
        int exp1 = explore(grid, x, y + 1, dp);
        if (exp1 > 0) {
            dp[x][y] += exp1;
        }
        int exp2 = explore(grid, x + 1, y, dp);
        if (exp2 > 0) {
            dp[x][y] += exp2;
        }
        return dp[x][y];
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{0}}},
                {new int[][]{{0, 0}, {0, 1}}},
                {new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}},
                {new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}}},
                {new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 1}}},
                {new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 0, 0, 0}}},
                {new int[][]{{0,0,0,0,0}, {0,0,0,0,1}, {0,0,0,1,0}, {0,0,0,0,0}}}
        };
    }
}