package com.mausoft.interview.common.util;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestExecutor {
    public static void runTestCases(Function<Object[], Object> function, Object[][] testCases) {
        Arrays.stream(testCases).map(e -> formatTestCase(function, e)).forEach(System.out::println);
    }

    private static String formatTestCase(Function<Object[], Object> function, Object[] params) {
        return new StringBuilder(formatTestCaseParams(params)).append(" -> ").append(formatTestCaseResults(execute(function, params))).toString();
    }
    private static Object execute(Function<Object[], Object> function, Object... params) {
        return function.apply(params);
    }

    public static void runTestCases(Consumer<Object[]> function, Object[][] testCases) {
        Arrays.stream(testCases).map(e -> formatTestCaseNoRetVal(function, e)).forEach(System.out::println);
    }

    private static String formatTestCaseNoRetVal(Consumer<Object[]> function, Object[] params) {
        StringBuilder strBuilder = new StringBuilder(formatTestCaseParams(params));
        execute(function, params);
        strBuilder.append(" -> ").append(formatTestCaseParams(params));
        return strBuilder.toString();
    }

    private static void execute(Consumer<Object[]> function, Object[] params) {
        function.accept(params);
    }

    private static String formatTestCaseParams(Object[] testCaseParams) {
        return formatParam(testCaseParams);
    }

    private static String formatTestCaseResults(Object testCaseResults) {
        return formatParam(testCaseResults);
    }

    private static String formatParam(Object param) {
        if (param.getClass().isArray()) {
            Object[] genericArray = convertToGenericArray(param);
            return formatParams(genericArray);
        }
        return String.valueOf(param);
    }

    private static Object[] convertToGenericArray(Object arr) {
        if (arr instanceof int[]) {
            return Arrays.stream(((int[]) arr)).boxed().toArray();
        }
        return (Object[]) arr;
    }

    private static String formatParams(Object[] arr) {
        if (arr.length == 0) {
            return "[]";
        }
        StringBuilder strBuilder = new StringBuilder("[");
        for (Object item : arr) {
            strBuilder.append(formatParam(item));
            strBuilder.append(", ");
        }
        strBuilder.delete(strBuilder.length() - 2, strBuilder.length());
        strBuilder.append("]");
        return strBuilder.toString();
    }
}