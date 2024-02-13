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
        Function<Object[], Object> function = e -> maxAmount((int[]) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
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