package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/maximum-depth-of-binary-tree
 *
 * You are given the root of a binary tree, and your task is to determine the maximum depth of this tree.
 * The maximum depth of a binary tree is determined by the count of nodes found on the longest pathway from the root node to the farthest leaf node.
 */
public class MaximumDepthInBinaryTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> maxDepth((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int maxDepth(TreeNode<Integer> node) {
        return maxDepth(node, new int[]{0});
    }

    private static int maxDepth(TreeNode<Integer> node, int[] max) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(node.getLeft()), maxDepth(node.getRight()));
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(3,9,20,null,null,15,7)},
                {from(3, null, from(9))},
                {from(6)},
                {from(9,7,20,3,15)},
                {from(-10,-23,45,-25)},
                {from(2, 1)}
        };
    }
}