package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.*;
import java.util.function.Function;

/**
 * Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B of size M x 2such that there is a edge directed from node
 * B[i][0] to node B[i][1].
 *
 * Find whether a path exists from node 1 to node A.
 * Return 1 if path exists else return 0.
 *
 * NOTE:
 *  - There are no self-loops in the graph.
 *  - There are no multiple edges between two nodes.
 *  - The graph may or may not be connected.
 *  - Nodes are numbered from 1 to A.
 *  - Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 *
 *  #uber
 */
public class PathDirectedGraph {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> pathExists((int) e[0], (int[][]) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int pathExists(int a, int[][] graph) {
        List<Integer>[] adjMatrix = buildAdjMatrix(a, graph);
        return pathExists(1, a, adjMatrix) ? 1 : 0;
    }

    private static List<Integer>[] buildAdjMatrix(int num, int[][] graph) {
        List<Integer>[] adjMatrix = new List[num + 1];
        for (int[] edge : graph) {
            adjMatrix[edge[0]] = adjMatrix[edge[0]] == null ? new ArrayList<>() : adjMatrix[edge[0]];
            adjMatrix[edge[0]].add(edge[1]);
        }
        return adjMatrix;
    }

    private static boolean pathExists(int start, int end, List<Integer>[] adjMatrix) {
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (curr == end) {
                return true;
            }
            for (Integer next : adjMatrix[curr]) {
                if (visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                queue.offer(next);
            }
        }
        return false;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {5, new int[][]{{1, 2}, {4, 1}, {2, 4}, {3, 4}, {5, 2}, {1, 3}}},
                {5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}}
        };
    }
}