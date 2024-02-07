package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/populating-next-right-pointers-in-each-node
 *
 * Given a perfect binary tree, where each node contains an additional pointer called next. This pointer is initially set to NULL for all nodes.
 * Your task is to connect all nodes of the same hierarchical level by setting the next pointer to its immediate right node.
 *
 * The next pointer of the rightmost node at each level is set to NULL.
 */
public class PopulatingNextRightPointers {
    public static void main(String... args) {
        Consumer<Object[]> function = e -> populate((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static void populate(TreeNode<Integer> node) {
        Deque<TreeNode<Integer>> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            TreeNode<Integer> next = null;
            for (int i = 0; i < queueSize; i++) {
                TreeNode<Integer> curr = queue.poll();
                curr.setNext(next);
                next = curr;
                if (curr.getRight() != null) {
                    queue.offer(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    queue.offer(curr.getLeft());
                }
            }
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {TreeNode.from(1, TreeNode.from(2, TreeNode.from(4), TreeNode.from(5)), TreeNode.from(3, TreeNode.from(6), TreeNode.from(7)))},
                {TreeNode.from(100, TreeNode.from(50, TreeNode.from(25), TreeNode.from(75)), TreeNode.from(200, TreeNode.from(300), TreeNode.from(10)))}
        };
    }

    public static class TreeNode<T> {
        private T value;
        private TreeNode<T> left;
        private TreeNode<T> right;
        private TreeNode<T> next;

        public TreeNode(T aValue) {
            value = aValue;
        }

        public TreeNode(T aValue, TreeNode<T> aLeft, TreeNode<T> aRight) {
            value = aValue;
            left = aLeft;
            right = aRight;
        }

        public T getValue() {
            return value;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public TreeNode<T> setLeft(TreeNode<T> aLeft) {
            left = aLeft;
            return this;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public TreeNode<T> setRight(TreeNode<T> aRight) {
            right = aRight;
            return this;
        }

        public TreeNode<T> getNext() {
            return next;
        }

        public TreeNode<T> setNext(TreeNode<T> aNext) {
            next = aNext;
            return this;
        }

        @Override
        public String toString() {
            return String.format("{%s%s%s%s}", "\"value\": " + value,
                    (left != null ? ", \"left\": " + left : ""),
                    (right != null ? ", \"right\": " + right : ""),
                    (next != null ? ", \"next\": " + next : ""));
        }

        public static <T> TreeNode<T> from(T value, TreeNode<T> left, TreeNode<T> right) {
            return new TreeNode<>(value, left, right);
        }

        public static <T> TreeNode<T> from(T value) {
            return from(value, null, null);
        }
    }
}