package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * Given a number n, calculate the corresponding Tribonacci number. The Tribonacci sequence Tn is defined as:
 *
 * T0 = 0, T1 = 1, T2 = 1 and Tn+3 =Tn + Tn+1 + Tn+2], for � > = 0 n>=0
 */
public class TribonacciNumber {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> tribonacci((int) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {4}, {5}, {25}
        };
    }
}