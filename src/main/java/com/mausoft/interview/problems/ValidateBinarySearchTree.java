package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;


/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/validate-binary-search-tree
 *
 * Given the root of a binary tree, check whether it is a valid binary search tree (BST).
 *
 * A binary tree is a valid BST if for every node:
 *  - The value of the node is greater than or equal to the value of its left node.
 *  - The value of the node is less than or equal to the value of its right node.
 *  - Both the left and right subtrees are valid BSTs.
 */
public class ValidateBinarySearchTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isValidBST((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static boolean isValidBST(TreeNode<Integer> node) {
        if (node == null) {
            return true;
        }
        if (isValidBST(node.getLeft()) && isValidBST(node.getRight())) {
            return validateLeft(node) && validateRight(node);
        }
        return false;
    }

    private static boolean validateLeft(TreeNode<Integer> node) {
        if (node.getLeft() == null) {
            return true;
        }
        return node.getValue() >= node.getLeft().getValue();
    }

    private static boolean validateRight(TreeNode<Integer> node) {
        if (node.getRight() == null) {
            return true;
        }
        return node.getValue() <= node.getRight().getValue();
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(5, from(3, from(6), from(4)), from(2))},
                {from(6, from(2, from(4), from(7)), from(5))},
                {from(4, from(2, from(1), from(3)), from(5))},
                {from(7, from(2, from(4), from(8)), from(5))},
                {from(9, from(5, from(1), from(3)), from(7))},
                {from(5, from(3, from(2), from(4)), from(8, null, from(9)))}
        };
    }
}