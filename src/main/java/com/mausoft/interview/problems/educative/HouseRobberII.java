package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * A professional robber plans to rob some houses along a street. These houses are arranged in a circle, which means that the first and the last house are neighbors.
 * The robber cannot rob adjacent houses because they have security alarms installed.
 *
 * Following the constraints mentioned above and given an integer array money representing the amount of money in each house, return the maximum amount
 * the robber can steal without alerting the police.
 */
public class HouseRobberII {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> maxAmountDp((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int maxAmountDp(int[] houses) {
        if (houses == null || houses.length == 0) {
            return 0;
        }
        if (houses.length == 1) {
            return houses[0];
        }
        if (houses.length == 2) {
            return Math.max(houses[0], houses[1]);
        }
        //start robbing from first house, skipping last
        int[] dp1 = new int[houses.length];
        dp1[0] = houses[0];
        dp1[1] = Math.max(houses[0], houses[1]);
        for (int i = 2; i < houses.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + houses[i]); //max amount I have robbed at each house
        }
        //start robbing from second house, include last
        int[] dp2 = new int[houses.length];
        dp2[1] = houses[1];
        dp2[2] = Math.max(houses[1], houses[2]);
        for (int i = 3; i < houses.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + houses[i]);
        }
        return Math.max(dp1[houses.length - 2], dp2[houses.length - 1]);
    }

    public static int maxAmount(int[] houses) { // O(2n)
        int[] max = new int[1];
        for (int i = 0; i < houses.length; i++) {
            max[0] = Math.max(max[0], maxAmount(houses, i, new HashSet<>(), max));
        }
        return max[0];
    }

    public static int maxAmount(int[] houses, int i, Set<Integer> visited, int[] max) {
        if (i >= houses.length) {
            return 0;
        }
        int adjLeft = (i + houses.length - 1) % houses.length;
        int adjRight = (i + 1) % houses.length; //next index
        if (visited.contains(adjLeft) || visited.contains(adjRight)) {
            return 0;
        }
        visited.add(i);
        int sum1 = houses[i] + maxAmount(houses, i + 2, visited, max);
        int sum2 = houses[i] + maxAmount(houses, i + 3, visited, max);
        visited.remove(i);
        return Math.max(sum1, sum2);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 5, 3}},
                {new int[]{1, 2, 3, 2}},
                {new int[]{1, 2, 10, 2}},
                {new int[]{2, 10, 14, 8, 1}},
                {new int[]{7, 4, 1, 9, 3}},
                {new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}}
        };
    }
}