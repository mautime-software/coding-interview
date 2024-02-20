package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * Given two integer arrays A and B of size N. There are N gas stations along a circular route, where the amount of gas at station i is A[i].
 *
 * You have a car with an unlimited gas tank and it costs B[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2.. Completing the circuit means starting at i and ending up at i again.
 */
public class GasStations {
    public static void main(String... args) {
        System.out.println((262114 + 1) % 262114);
        Function<Object[], Object> function = e -> gasStations((int[]) e[0], (int[]) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int gasStations(int[] stations, int[] distances) { //Accepted solution
        for (int i = 0; i < stations.length; i++) {
            int visited = 0;
            int next = i;
            int gas = stations[i];
            while (gas >= distances[next]) {
                visited++;
                if (visited == stations.length) {
                    return i;
                }
                gas -= distances[next];
                next = (next + 1) % stations.length;
                gas += stations[next];
            }
        }
        return -1;
    }

    private static boolean canComplete(int[] stations, int[] distances, int i, int gas, int visited) { //This solution will overflow with very large inputs
        if (gas < distances[i]) {
            return false;
        }
        visited++;
        if (visited == stations.length) {
            return true;
        }
        int next = (i + 1) % stations.length;
        return canComplete(stations, distances, next, gas - distances[i] + stations[next], visited);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}}
        };
    }
}