package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.*;
import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/vertical-order-traversal-of-a-binary-tree
 *
 * Find the vertical order traversal of a binary tree when the root of the binary tree is given.
 * In other words, return the values of the nodes from top to bottom in each column, column by column from left to right.
 * If there is more than one node in the same column and row, return the values from left to right.
 */
public class VerticalOrderTraversalBinaryTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> verticalTraversal((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static List<List<Integer>> verticalTraversal(TreeNode<Integer> node) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Deque<Map.Entry<Integer, TreeNode<Integer>>> queue = new ArrayDeque<>();
        queue.offer(new AbstractMap.SimpleEntry<>(0, node));
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Map.Entry<Integer, TreeNode<Integer>> curr = queue.poll();
                map.computeIfAbsent(curr.getKey(), k -> new ArrayList<>()).add(curr.getValue().getValue());
                if (curr.getValue().getLeft() != null) {
                    queue.offer(new AbstractMap.SimpleEntry<>(curr.getKey() - 1, curr.getValue().getLeft()));
                    min = Math.min(curr.getKey() - 1, min);
                }
                if (curr.getValue().getRight() != null) {
                    queue.offer(new AbstractMap.SimpleEntry<>(curr.getKey() + 1, curr.getValue().getRight()));
                    max = Math.max(curr.getKey() + 1, max);
                }
            }
        }

        int j = min;
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            results.add(map.get(j++));
        }
        return results;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(7, from(6, from(5), from(8)), from(11))},
                {from(12, from(6, from(2), from(7, from(5), from(10))), from(20))},
                {from(100, from(50, from(25, from(350), from(15)), from(75)), from(200, from(300), from(10)))},
                {from(20, from(40, from(90), from(67)), from(50, from(94), null))},
                {from(9, from(7, null, from(1, from(8, null, from(12)), from(10))), null)},
                {from(3, from(2, null, from(3)), from(3, null, from(1)))}
        };
    }
}