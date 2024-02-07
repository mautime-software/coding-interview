package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/longest-substring-without-repeating-characters
 *
 * Given a string, str, return the length of the longest substring without repeating characters.
 */
public class LongestSubstringWithoutRepeating {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> longestSubstring((String) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"abcdbea"},
                {"aba"},
                {"abccabcabcc"},
                {"aaaabaaa"},
                {"bbbbb"}
        };
    }

    public static int longestSubstring(String str) {
        int i = 0;
        int j = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < str.length()) {
            map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
            while(i < j && map.get(str.charAt(j)) > 1) {
                map.put(str.charAt(i), map.get(str.charAt(i)) - 1);
                i++;
            }
            longest = Math.max(longest, j - i + 1);
            j++;
        }
        return longest;
    }
}