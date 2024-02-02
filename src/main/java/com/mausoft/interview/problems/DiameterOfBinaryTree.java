package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.function.Function;
import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/diameter-of-binary-tree
 *
 * Given a binary tree, you need to compute the length of the tree’s diameter.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 */
public class DiameterOfBinaryTree {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> diameterWrapper((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int diameterWrapper(TreeNode<Integer> node) {
        int[] nodeDiameter = new int[1];
        diameter(node, nodeDiameter);
        return nodeDiameter[0];
    }

    public static int diameter(TreeNode<Integer> node, int[] nodeDiameter) {
        if (node == null) {
            return 0;
        }
        int leftDiameter = diameter(node.getLeft(), nodeDiameter);
        int rightDiameter = diameter(node.getRight(), nodeDiameter);
        nodeDiameter[0] = Math.max(nodeDiameter[0], (leftDiameter + rightDiameter));
        return 1 + Math.max(leftDiameter, rightDiameter);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(1, from(2), from(3, from(4, from(6), null), from(5)))},
                {from(5, from(6), from(7, from(8), from(9)))},
                {from(2, from(1, from(3), from(5)), from(4, from(6), from(7)))},
                {from(1, from(2, from(4, from(8), from(9)), from(5)), from(3, from(6), from(7)))},
                {from(9, from(7, null, from(1, from(8, null, from(12)), from(10))), null)}
        };
    }
}