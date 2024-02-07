package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/reorganize-string
 *
 * Given a string, str, rearrange it so that any two adjacent characters are not the same.
 * If such a reorganization of the characters is possible, output any possible valid arrangement. Otherwise, return an empty string.
 */
public class ReorganizeString {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> reorganize((String) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static String reorganize(String str) {
        Map<Character, Integer> frequencyMap = calculateFrequency(str);
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.offer(entry);
        }
        StringBuilder strBuilder = new StringBuilder(str.length());
        Map.Entry<Character, Integer> prev = null;
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> maxFrequent = maxHeap.poll();
            strBuilder.append(maxFrequent.getKey());

            if (prev != null) {
                maxHeap.offer(prev);
                prev = null;
            }
            if (maxFrequent.getValue() - 1 > 0) {
                prev = new AbstractMap.SimpleEntry<>(maxFrequent.getKey(), maxFrequent.getValue() - 1);
            }
        }

        if (prev != null) {
            return "";
        }
        return strBuilder.toString();
    }

    private static Map<Character, Integer> calculateFrequency(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"programming"},
                {"hello"},
                {"fofjjb"},
                {"abbacdde"},
                {"aba"},
                {"awesome"},
                {"aaab"},
                {"aab"}
        };
    }
}