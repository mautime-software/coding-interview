package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/longest-valid-parentheses/
 *
 * Given a string A containing just the characters ’(‘ and ’)’.
 *
 * Find the length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParentheses {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> longestValidParentheses((String) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int longestValidParentheses(String str) {
        int j = 0;
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // top element from the stack represents either the index of a matching opening parentheses or the start of a potential valid string
        while (j < str.length()) {
            if (str.charAt(j) == '(') {
                stack.push(j);
            } else {
                stack.pop(); // pop first element, which should be the corresponding opening parentheses
                if (!stack.isEmpty()) { // if stack is not empty after popping, then calculate length
                    max = Math.max(max, j - stack.peek());
                } else { // if empty after popping, it means the current closing parentheses doesn't have corresponding opening parentheses
                    stack.push(j); // push index of closing parentheses to mark the start of another potential valid substring
                }
            }
            j++;
        }
        return max;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"(()"},
                {")()())"},
                {"()(()"},
                {"(((())))"}
        };
    }
}