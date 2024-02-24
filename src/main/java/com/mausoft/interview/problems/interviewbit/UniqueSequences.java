package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/distinct-subsequences/
 *
 * Given two sequences A, B, count number of unique ways in sequence A, to form a subsequence that is identical to the sequence B.
 *
 * Subsequence : A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters. (ie, “ACE” is a subsequence of “ABCDE” while “AEC” is not).
 */
public class UniqueSequences {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> subsequences((String) e[0], (String) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int subsequences(String str, String target) {
        return countSubsequences(str, target, 0, 0, new HashMap<>());
    }

    private static int countSubsequences(String str, String target, int i, int j, Map<String, Integer> dp) { // O(m * n)
        if (i == str.length()) { // if reached the end of source string then we can form str from target
            return 1;
        }
        if (j == target.length()) { // if reached the end of target string but not end of str string then we can't form str from target
            return 0;
        }
        String key = i + "_" + j;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int count = 0;
        if (str.charAt(i) == target.charAt(j)) { // if both characters match then we have two options: include or exclude current character from target
            count = countSubsequences(str, target, i + 1, j + 1, dp) + countSubsequences(str, target, i, j + 1, dp);
        } else { // if not matched then we can only exclude character from target
            count = countSubsequences(str, target, i, j + 1, dp);
        }
        dp.put(key, count);
        return count;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"rabbit", "rabbbit"},
                {"rabbit", "rabbbbit"},
                {"bbababa", "aaaababbababbaabbaaababaaabbbaaabbb"},
                {"aaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3pp",
                        "aaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3ppaaaaabbbccdfnrhgoiwhoitrhgfnewiprgnhiotqhgioreqgnpiewhpiqjpergptiwhjpijhpoqrejhpetjipjio2qj5ipjp3pp"}
        };
    }
}