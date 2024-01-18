package com.mausoft.interview.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestExecutor {
    public static <U> void runTestCases(Function<Object[], U> function, Object[][] testCases) {
        printTestCaseResults(execute(function, testCases));
    }

    private static <U> List<TestResults> execute(Function<Object[], U> function, Object[][] testCases) {
        return Arrays.stream(testCases).map(e -> new TestResults(e, execute(function, e))).collect(Collectors.toList());
    }

    private static <T, U> U execute(Function<T[], U> function, T... params) {
        return function.apply(params);
    }

    public static void runTestCases(Consumer<Object[]> function, Object[][] testCases) {
        printTestCaseResults(execute(function, testCases));
    }

    private static <T> List<TestResults> execute(Consumer<Object[]> function, Object[][] testCases) {
        return Arrays.stream(testCases).peek(function).map(e -> new TestResults(e)).collect(Collectors.toList());
    }

    private static void printTestCaseResults(List<TestResults> testCaseResults) {
        if (testCaseResults.get(0).getResult() == null) {
            testCaseResults.forEach(e -> System.out.println(formatParams(e.getParams())));
            return;
        }
        testCaseResults.forEach(e -> System.out.println(formatParams(e.getParams()) + " -> " + formatParam(e.getResult())));
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
        StringBuilder strBuilder = new StringBuilder("[");
        for (Object item : arr) {
            strBuilder.append(formatParam(item));
            strBuilder.append(", ");
        }
        strBuilder.delete(strBuilder.length() - 2, strBuilder.length());
        strBuilder.append("]");
        return strBuilder.toString();
    }

    private static class TestResults {
        private Object[] params;
        private Object result;

        public TestResults(Object[] aParams) {
            params = aParams;
        }

        public TestResults(Object[] aParams, Object aResult) {
            params = aParams;
            result = aResult;
        }

        public Object[] getParams() {
            return params;
        }

        public Object getResult() {
            return result;
        }
    }
}