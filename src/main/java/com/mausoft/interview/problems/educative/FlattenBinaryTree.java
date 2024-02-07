package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.from;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/flatten-binary-tree-to-linked-list
 *
 * Given the root of a binary tree, the task is to flatten the tree into a linked list using the same TreeNode class.
 * The left child pointer of each node in the linked list should always be NULL, and the right child pointer should point to the next node in the linked list.
 * The nodes in the linked list should be in the same order as that of the preorder traversal of the given binary tree.
 */
public class FlattenBinaryTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> flatten((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static TreeNode<Integer> flatten(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        TreeNode<Integer> newNode = new TreeNode<>(node.getValue());
        TreeNode<Integer> fLeft = flatten(node.getLeft());
        TreeNode<Integer> fRight = flatten(node.getRight());
        newNode.setRight(fLeft);
        TreeNode<Integer> last = newNode;
        while (fLeft != null) {
            last = fLeft;
            fLeft = fLeft.getRight();
        }
        last.setRight(fRight);
        return newNode;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new TreeNode<>(1).setLeft(new TreeNode<>(2).setLeft(new TreeNode<>(3)).setRight(new TreeNode<>(4).setLeft(new TreeNode<>(5)).setRight(new TreeNode<>(6))))},
                {new TreeNode<>(3, new TreeNode<>(2, new TreeNode<>(1), new TreeNode<>(4)), new TreeNode<Integer>(17, new TreeNode<>(19), new TreeNode<>(5)))},
                {TreeNode.from(7, TreeNode.from(6, TreeNode.from(4, TreeNode.from(1), null), TreeNode.from(3)), new TreeNode<Integer>(5, TreeNode.from(2), null))},
                {from(5, from(4, from(3, from(1), from(9)), from(2)), from(6, from(7), from(8)))},
                {from(5, from(2, from(6), from(10)), from(1, from(11), from(44)))},
                {from(1, from(2, from(3), from(4)), from(5, from(6), null))},
                {from(-1, from(-2, from(-5, from(2), null), from(1, from(6), null)), null)}
        };
    }
}