package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/build-binary-tree-from-preorder-and-inorder-traversal
 *
 * Create a binary tree from two integer arrays, pOrder and iOrder, where pOrder represents a preorder traversal of a binary tree,
 * and iOrder represents an inorder traversal of the same tree.
 */
public class BinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> buildTree((int[]) e[0], (int[]) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static TreeNode<Integer> buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildNode(preorder, inorder, 0, preorder.length - 1, new int[]{0}, inorderMap);
    }

    private static TreeNode<Integer> buildNode(int[] preorder, int[] inorder, int i, int j, int[] p, Map<Integer, Integer> inorderMap) {
        if (i > j) {
            return null;
        }
        int curr = p[0];
        TreeNode<Integer> node = new TreeNode<>(preorder[curr]);
        p[0]++;
        if (i == j) {
            return node;
        }
        node.setLeft(buildNode(preorder, inorder, i, inorderMap.get(preorder[curr]) - 1, p, inorderMap));
        node.setRight(buildNode(preorder, inorder, inorderMap.get(preorder[curr]) + 1, j, p, inorderMap));
        return node;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}},
                {new int[]{10,20,40,50,30,60}, new int[]{40,20,50,10,60,30}},
                {new int[]{1, 2, 4, 5, 3, 6}, new int[]{4, 2, 5, 1, 6, 3}},
                {new int[]{1, 2, 4, 7, 3}, new int[]{4, 2, 7, 1, 3}},
                {new int[]{1, 2, 4, 8, 9, 5, 3, 6, 7}, new int[]{8, 4, 9, 2, 5, 1, 6, 3, 7}}
        };
    }
}