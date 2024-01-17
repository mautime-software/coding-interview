package com.mausoft.interview.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestExecutor {
    public static <U> void runTestCases(Function<Object[], U> function, Object[][] testCases) {
        printTestCaseResults(execute(function, testCases));
    }

    public static <U> List<Object[]> execute(Function<Object[], U> function, Object[][] testCases) {
        return Arrays.stream(testCases).map(e -> new Object[]{e, execute(function, e)}).collect(Collectors.toList());
    }

    public static <T, U> U execute(Function<T[], U> function, T... params) {
        return function.apply(params);
    }

    private static void printTestCaseResults(List<Object[]> testCaseResults) {
        testCaseResults.forEach(e -> System.out.println(formatParams((Object[]) e[0]) + " -> " + formatParam(e[1])));
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
}