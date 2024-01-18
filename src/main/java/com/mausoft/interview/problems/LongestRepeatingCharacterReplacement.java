package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/longest-repeating-character-replacement
 *
 * Given a string, s, of lowercase English characters and an integer, k, return the length of the longest substring after replacing at most k characters
 * with any other lowercase English character so that all the characters in the substring are the same.
 *
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> longestSubstring((String) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"aaacbbbaabab", 2},
                {"aaacbbbaabab", 1},
                {"dippitydip", 4},
                {"coollooc", 2},
                {"aaaaaaaaaa", 2}
        };
    }
    public static int longestSubstring(String str, int k) {
        int i = 0;
        int j = 0;
        int maxFrequency = 0;
        int max = 0;
        Map<Character, Integer> frequencyMap = new HashMap<>();
        while (j < str.length()) {
            frequencyMap.put(str.charAt(j), frequencyMap.getOrDefault(str.charAt(j), 0) + 1);
            if (frequencyMap.get(str.charAt(j)) >= maxFrequency) {
                maxFrequency = frequencyMap.get(str.charAt(j));
            }
            if (((j - i + 1) - maxFrequency) > k) {
                frequencyMap.put(str.charAt(i), frequencyMap.get(str.charAt(i)) - 1);
                i++;
            }
            max = Math.max(max, j - i + 1);
            j++;
        }
        return max;
    }
}