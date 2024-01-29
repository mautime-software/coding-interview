package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * Given two strings, str1 and str2, find the shortest substring in str1 such that str2 is a subsequence of that substring.
 *
 * A substring is defined as a contiguous sequence of characters within a string. A subsequence is a sequence that can be derived
 * from another sequence by deleting zero or more elements without changing the order of the remaining elements.
 *
 * str = abcdebdde
 * target = bde
 */
public class MinimumWindowSubsequence {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> minWindowSubsequence((String) e[0], (String) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static String minWindowSubsequence(String str, String target) {
        int i = 0;
        int j = 0;
        int minLength = Integer.MAX_VALUE;
        String minSubstring = "";
        while (i < str.length()) {
            if (str.charAt(i) == target.charAt(j)) {
                j++;
                if (j == target.length()) {
                    int k = i;
                    while (j > 0) {
                        if (str.charAt(i) == target.charAt(j - 1)) {
                            j--;
                        }
                        i--;
                    }
                    i++;
                    if ((k - i + 1) < minLength) {
                        minLength = (k - i) + 1;
                        minSubstring = str.substring(i, k + 1);
                    }
                }
            }
            i++;
        }
        return minSubstring;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"abcdebdde", "bcde"},
                {"fgrqsqsnodwmxzkzxwqegkndaa", "kzed"},
                {"michmznaitnjdnjkdsnmichmznait", "michmznait"},
                {"afgegrwgwga", "aa"},
                {"abcdbebe", "bbe"},
                {"abcdedeaqdweq", "adeq"},
                {"zxcvnhss", "css"},
                {"alpha", "la"},
                {"beta", "ab"}
        };
    }
}