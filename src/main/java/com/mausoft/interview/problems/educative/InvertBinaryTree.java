package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/invert-binary-tree
 *
 * Given the root node of a binary tree, transform the tree by swapping each node’s left and right subtrees, thus creating a mirror image of the original tree.
 * Return the root of the transformed tree.
 */
public class InvertBinaryTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> invert((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static TreeNode<Integer> invert(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        TreeNode<Integer> leftInverted = invert(node.getLeft());
        TreeNode<Integer> rightInverted = invert(node.getRight());
        node.setRight(leftInverted);
        node.setLeft(rightInverted);
        return node;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(10, from(5, from(2), from(7)), from(20))},
                {from(100, from(50, from(25), from(75)), from(200, from(125), from(350)))},
                {from(100, from(200, from(400), from(500)), from(300))},
                {from(350, from(125, from(75), from(50)), from(100, from(25), null))},
                {from(1, from(2, from(3, from(4), null), null), null)},
                {from(1, from(2, from(4), null), from(3, null, from(5)))}
        };
    }
}