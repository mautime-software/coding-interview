package com.mausoft.interview.problems.oracle;

import com.mausoft.interview.common.util.StringUtils;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

/**
 * Reverse enclosed string within parentheses
 *
 * #oracle #interview
 */
public class StringParenthesesReverse {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> reverse((String) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        reverse(str, 0, new ArrayDeque<>(), reversed);
        return reversed.toString();
    }

    private static void reverse(String str, int i, Deque<Integer> stack, StringBuilder word) {
        if (i == str.length()) {
            return;
        }
        if (str.charAt(i) == '(') {
            stack.push(word.length());
        } else if (str.charAt(i) == ')') {
            StringUtils.reverse(word, stack.pop(), word.length() - 1);
        } else {
            word.append(str.charAt(i));
        }
        reverse(str, i + 1, stack, word);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {"(abc)"},
                {"ab(abc)"},
                {"ab(a(bc)df(ab))"},
                {"ab(a(bc)df(ab))xy"},
                {"ab(a(bc)df(ab)xy)"},
                {"((abc)df(ghi))"}
        };
    }
}