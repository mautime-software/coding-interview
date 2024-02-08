package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;

/**
 * https://www.interviewbit.com/problems/max-edge-queries/
 *
 * Given a tree with N nodes numbered from 1 to N.
 * Each edge is bi-directional and has a certain weight assigned to it.
 * You are given Q queries, in each query you are given two integers u and v and you are required to find the maximum weighted edge in a simple path from u to v.
 * You have to return the weight of the edge for each queries.
 */
public class MaxEdgeQueries {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> maxWeights((int[][]) e[0], (int[][]) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int[] maxWeights(int[][] graph, int[][] targets) {
        List<Integer[]>[] adjMatrix = buildAdjMatrix(graph);
        return maxWeights(adjMatrix, targets);
    }

    private static List<Integer[]>[] buildAdjMatrix(int[][] graph) {
        int maxNode = maxNode(graph);
        List<Integer[]>[] adjMatrix = new List[maxNode];
        for (int[] edge : graph) {
            adjMatrix[edge[0]] = adjMatrix[edge[0]] == null ?new ArrayList<>() : adjMatrix[edge[0]];
            adjMatrix[edge[0]].add(new Integer[]{edge[1], edge[2]});
            adjMatrix[edge[1]] = adjMatrix[edge[1]] == null ? new ArrayList<>() : adjMatrix[edge[1]];
            adjMatrix[edge[1]].add(new Integer[]{edge[0], edge[2]});
        }
        return adjMatrix;
    }

    private static int maxNode(int[][] graph) {
        int max = Integer.MIN_VALUE;
        for (int[] edge : graph) {
            max = Math.max(edge[0], edge[1]);
        }
        return max + 1;
    }

    private static int[] maxWeights(List<Integer[]>[] adjMatrix, int[][] targets) {
        int[] results = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            int[] target = targets[i];
            int[] max = new int[adjMatrix.length];
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(target[0]);
            visited.add(target[0]);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (curr == target[1]) {
                    results[i] = max[curr];
                    break;
                }
                if (adjMatrix[curr] == null) {
                    continue;
                }
                for (Integer[] edge : adjMatrix[curr]) {
                    int dest = edge[0];
                    if (visited.contains(dest)) {
                        continue;
                    }
                    visited.add(dest);
                    int weight = edge[1];
                    max[dest] = Math.max(weight, max[curr]);
                    queue.offer(dest);
                }
            }
        }
        return results;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[][]{{1, 2, 11}, {1, 3, 1}, {2, 4, 12}, {2, 5, 100}}, new int[][]{{3, 5}, {2, 3}}}
        };
    }
}