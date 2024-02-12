package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/lowest-common-ancestor-in-a-binary-tree
 *
 * Given the root node of a binary tree with n nodes, your task is to find the lowest common ancestor of two of its nodes, p and q.
 *
 * Note: The lowest common ancestor of two nodes, p and q, is defined as the lowest node in the binary tree that has both p and q as descendants.
 *
 * A node can also be a descendant of itself. For example, if q is a descendant of p, and we know that p is a descendant of itself,
 * then p will be the lowest common ancestor of p and q.
 */
public class LowestCommonAncestor {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> lowestAncestor((TreeNode<Integer>) e[0], (int) e[1], (int) e[2]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static Integer lowestAncestor(TreeNode<Integer> node, int p, int q) {
        if (node == null) {
            return null;
        }
        if (node.getValue().equals(p) || node.getValue().equals(q)) {
            return node.getValue();
        }

        Integer left = lowestAncestor(node.getLeft(), p, q);
        Integer right = lowestAncestor(node.getRight(), p, q);
        if (left != null && right != null) {
            return node.getValue();
        }
        return left != null ? left : right;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(1, from(2, from(4), from(5)), from(3, from(6), from(7))), 4, 5},
                {from(6, from(9, from(3), null), from(4, null, from(8))), 9, 3},
                {from(100, from(50, from(25), from(75)), from(200, from(350), null)), 25, 75},
                {from(100, from(200, from(50), from(25)), from(75, from(350), null)), 50, 350},
                {from(350, from(100, from(50), from(200)), from(75, from(25), null)), 100, 200},
                {from(25, from(50, from(100), from(200)), from(75, from(350), null)), 350, 200},
        };
    }
}