package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/valid-palindrome-ii
 *
 * Write a function that takes a string as input and checks whether it can be a valid palindrome by removing at most one character from it.
 */
public class ValidPalindromeII {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isValidPalindrome((String) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static boolean isValidPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i <= j) {
            if (str.charAt(i) != str.charAt(j)) {
                return isPalindrome(str.substring(i + 1, j + 1)) || isPalindrome(str.substring(i, j));
            }
            i++;
            j--;
        }
        return false;
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
                {"madame"},
                {"dead"},
                {"abca"},
                {"tebbem"},
                {"eeccccbebaeeabebccceea"}
        };
    }
}