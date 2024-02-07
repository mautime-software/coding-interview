package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.*;
import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/symmetric-tree
 *
 * Given the root of a binary tree, check whether it is a symmetric tree. A symmetric tree refers to a tree that is a mirror of itself, i.e., symmetric around its root.
 */
public class SymmetricTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isSymmetricOptimized((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    private static boolean isSymmetricOptimized(TreeNode<Integer> node) { // O(n)
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(node.getLeft());
        queue.offer(node.getRight());
        while(!queue.isEmpty()) {
            TreeNode<Integer> left = queue.poll();
            TreeNode<Integer> right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (!left.getValue().equals(right.getValue())) {
                return false;
            }
            queue.offer(left.getLeft());
            queue.offer(right.getRight());
            queue.offer(left.getRight());
            queue.offer(right.getLeft());
        }
        return true;
    }

    public static boolean isSymmetric(TreeNode<Integer> node) {
        Deque<TreeNode<Integer>> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<TreeNode<Integer>> level = new ArrayList<>();
            for (int i = 0; i < queueSize; i++) {
                TreeNode<Integer> curr = queue.poll();
                if (curr.getRight() != null) {
                    level.add(curr.getRight());
                    queue.offer(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    level.add(curr.getLeft());
                    queue.offer(curr.getLeft());
                }
            }

            if (level.size() > 0 && level.size() % 2 != 0) {
                return false;
            }
            if (!isSymmetric(level)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSymmetric(List<TreeNode<Integer>> nodes) {
        int i = 0;
        int j = nodes.size() - 1;
        while (i < j) {
            if (!nodes.get(i).getValue().equals(nodes.get(j).getValue())) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(1, from(2, from(3), from(4)), from(2, from(4), from(3)))},
                {from(18, from(21, from(47), from(20)), from(21, from(21), from(47)))},
                {from(25, from(4, from(2), from(3)), from(67, from(3), from(2)))},
                {from(1, from(2, from(3), null), from(2, null, from(3)))},
                {from(1, from(2, null, from(3, from(4), from(5))), from(2, from(3, from(5), from(4)), null))}
        };
    }
}