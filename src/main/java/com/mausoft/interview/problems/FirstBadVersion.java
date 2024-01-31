package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/first-bad-version
 *
 * The latest version of a software product fails the quality check. Since each version is developed upon the previous one,
 * all the versions created after a bad version are also considered bad.
 *
 * Suppose you have n versions with the IDs [1,2,...,n], and you have access to an API function that returns TRUE if the argument is the ID of a bad version.
 *
 * Find the first bad version that is causing all the later ones to be bad. Additionally,
 * the solution should also return the number of API calls made during the process and should minimize the number of API calls too.
 */
public class FirstBadVersion {
    private static int badVersion = -1;

    public static void main(String... args) {
        Function<Object[], Object> function = e -> findFirstBadVersionWrapper((int) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static int[] findFirstBadVersionWrapper(int versions, int badVersion) {
        FirstBadVersion.badVersion = badVersion;
        int[] results = new int[2];
        search(0, versions - 1, results);
        return results;
    }


    private static void search(int i, int j, int[] results) {
        if (i > j) {
            return;
        }
        int mid = (i + j) / 2;
        results[1] += 1;
        if (isBadVersion(mid + 1)) {
            results[0] = mid + 1;
            search(i, mid - 1, results);
        } else {
            search(mid + 1, j, results);
        }
    }

    private static boolean isBadVersion(int version) {
        return version >= badVersion;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {38, 28},
                {13, 10},
                {29, 10},
                {40, 28},
                {23, 19},
        };
    }
}