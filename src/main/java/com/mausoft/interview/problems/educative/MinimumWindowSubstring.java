package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Given two strings, s and t, find the minimum window substring in s, which has the following properties:
 *
 *  - It is the shortest substring of s that includes all of the characters present in t.
 *  - It must contain at least the same frequency of each character as in t.
 *  - The order of the characters does not matter here.
 */
public class MinimumWindowSubstring {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> findMinSubstring((String) e[0], (String) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static String findMinSubstring(String str, String target) {
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : target.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        int i = 0;
        int j = 0;
        int matches = 0;
        int min = Integer.MAX_VALUE;
        String substring = "";
        while (j < str.length()) {
            if (targetMap.containsKey(str.charAt(j))) {
                targetMap.put(str.charAt(j), targetMap.get(str.charAt(j)) - 1);
                matches += targetMap.get(str.charAt(j)) >= 0 ? 1 : 0;
            }
            while (i <= j && matches == target.length()) {
                if ((j - i + 1) < min) {
                    substring = str.substring(i, j + 1);
                    min = j - i + 1;
                }
                if (targetMap.containsKey(str.charAt(i))) {
                    targetMap.put(str.charAt(i), targetMap.get(str.charAt(i)) + 1);
                    if (targetMap.get(str.charAt(i)) > 0) {
                        matches--;
                    }
                }
                i++;
            }
            j++;
        }
        return substring;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"ABCD", "ABC"},
                {"XYZYX", "XYZ"},
                {"ABXYZJKLSNFC", "ABC"},
                {"AAAAAAAAAAA", "A"},
                {"ABDFGDCKAB", "ABCD"},
                {"PATTERN", "TN"},
                {"LIFE", "I"},
                {"ABRACADABRA", "ABC"},
                {"STRIKER", "RK"},
                {"DFFDFDFVD", "VDD"}
        };
    }
}