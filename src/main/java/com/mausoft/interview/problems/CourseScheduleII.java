package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/course-schedule-ii
 *
 * Let’s assume that there are a total of n courses labeled from 0 to n-1. Some courses may have prerequisites. A list of prerequisites is specified such that if
 * Prerequisites[i] = [a, b], you must take course b before a
 *
 * Given the total number of courses and a list of the prerequisite pairs, return the course order a student should take to finish all of the courses.
 * If there are multiple valid orderings of courses, then the return any one of them.
 */
public class CourseScheduleII {
    private static final int VISITING = 1;
    private static final int VISITED = 2;

    public static void main(String... args) {
        Function<Object[], Object> function = e -> courseSchedule((int) e[0], (int[][]) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int[] courseSchedule(int n, int[][] prerequisites) {
        List<Integer>[] adjMatrix = buildAdjMatrix(n, prerequisites);
        Map<Integer, Integer> visit = new HashMap<>();
        for (int i = 0; i < n; i++) {
            visit.put(i, 0);
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            traverseGraph(adjMatrix, i, visit, stack);
        }
        if (stack.size() == n) {
            return prepareCourseSchedule(stack);
        }
        return null;
    }

    private static List<Integer>[] buildAdjMatrix(int n, int[][] prerequisites) {
        List<Integer>[] adjMatrix = new List[n];
        for (int i = 0; i < n; i++) {
            adjMatrix[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            adjMatrix[prerequisite[1]].add(prerequisite[0]);
        }
        return adjMatrix;
    }

    private static boolean traverseGraph(List<Integer>[] adjMatrix, int i, Map<Integer, Integer> visit, LinkedList<Integer> stack) {
        if (visit.get(i) == VISITING) {
            return false; //detect cycle
        }
        if (visit.get(i) == VISITED) {
            return true;
        }
        visit.put(i, VISITING);
        for (Integer pre : adjMatrix[i]) {
            if (!traverseGraph(adjMatrix, pre, visit, stack)) {
                return false;
            }
        }
        visit.put(i, VISITED);
        stack.push(i);
        return true;
    }

    private static int[] prepareCourseSchedule(LinkedList<Integer> stack) {
        int[] schedule = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            schedule[i++] = stack.pop();
        }
        return schedule;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {4, 3}}},
                {4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}},
                {2, new int[][]{{1, 0}}},
                {7, new int[][]{{1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}}}
        };
    }
}