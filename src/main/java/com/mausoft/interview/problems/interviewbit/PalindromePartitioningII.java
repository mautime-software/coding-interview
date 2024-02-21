package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/palindrome-partitioning-ii/
 *
 * Given a string A, partition A such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of A.
 */
public class PalindromePartitioningII {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> minCut((String) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int minCut(String str) {
        int n = str.length();
        int[] cuts = new int[n];
        return minCuts(str, 0, n - 1, cuts);
    }

    private static int minCuts(String str, int i, int j, int[] cuts) {
        if (i >= j || isPalindrome(str.substring(i, j + 1))) {
            return 0; // If the substring is already a palindrome, no cuts needed
        }
        if (cuts[i] != 0) {
            return cuts[i]; // If already calculated, return the value
        }
        int minCuts = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            if (isPalindrome(str.substring(i, k + 1))) {
                minCuts = Math.min(minCuts, 1 + minCuts(str, k + 1, j, cuts));
            }
        }
        cuts[i] = minCuts;
        return minCuts;
    }

    private static boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"T"},
                {"aba"},
                {"aab"}
        };
    }
}