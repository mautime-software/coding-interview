package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/binary-tree-maximum-path-sum
 *
 * Given the root of a binary tree, return the maximum sum of any non-empty path.
 *
 * A path in a binary tree is defined as follows:
 *  - A sequence of nodes in which each pair of adjacent nodes must have an edge connecting them.
 *  - A node can only be included in a path once at most.
 *  - Including the root in the path is not compulsory.
 *
 * You can calculate the path sum by adding up all node values in the path. To solve this problem, calculate the maximum path sum given the root of a binary tree so that there won’t be any greater path than it in the tree.
 */
public class BinaryTreeMaxPathSum {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> maxSum((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int maxSum(TreeNode<Integer> node) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxSum(node, max);
        return max[0];
    }

    public static int maxSum(TreeNode<Integer> node, int[] max) {
        if (node == null) {
            return 0;
        }
        int leftSum = Math.max(maxSum(node.getLeft(), max), 0); //Ignore negative sums
        int rightSum = Math.max(maxSum(node.getRight(), max), 0); //Ignore negative sums
        max[0] = Math.max(node.getValue() + leftSum + rightSum, max[0]);
        return node.getValue() + Math.max(leftSum, rightSum);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(-8, from(2, from(1), from(4)), from(17, from(19), from(5)))},
                {from(7, from(3, from(-1), from(-3)), from(4))},
                {from(-1, from(-2), from(-3))},
                {from(-10, from(-9), from(20, from(15), from(7)))},
                {from(1, from(-3, from(5), null), from(3, null, from(-5)))}
        };
    }
}