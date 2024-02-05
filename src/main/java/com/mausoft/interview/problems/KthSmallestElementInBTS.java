package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.PriorityQueue;
import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/kth-smallest-element-in-a-bst
 *
 * Given the root node of a binary search tree and an integer value k, return the kth smallest value in the tree
 */
public class KthSmallestElementInBTS {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> min((TreeNode<Integer>) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int min(TreeNode<Integer> node, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        min(node, k, maxHeap);
        return maxHeap.peek();
    }

    private static void min(TreeNode<Integer> node, int k, PriorityQueue<Integer> maxHeap) {
        if (node == null) {
            return;
        }
        if (maxHeap.size() < k) {
            maxHeap.offer(node.getValue());
        } else if (node.getValue() < maxHeap.peek()) {
            maxHeap.poll();
            maxHeap.offer(node.getValue());
        }
        min(node.getLeft(), k, maxHeap);
        min(node.getRight(), k, maxHeap);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(2, from(1), from(3)), 2},
                {from(3, from(1), from(4, null, from(10))), 1},
                {from(23, from(21, null, from(22)), from(24)), 3},
                {from(10, from(8), null), 1},
                {from(10, null, from(18)), 2}
        };
    }
}