package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/connect-all-siblings-of-a-binary-tree
 *
 * Given the root of a perfect binary tree, where each node is equipped with an additional pointer, next,
 * connect all nodes from left to right. Do so in such a way that the next pointer of each node points to its immediate right sibling except for the rightmost node,
 * which points to the first node of the next level.
 *
 * The next pointer of the last node of the binary tree (i.e., the rightmost node of the last level) should be set to NULL.
 */
public class ConnectSiblingsBinaryTree {
    public static void main(String... args) {
        Consumer<Object[]> function = e -> connectSiblings((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static void connectSiblings(TreeNode<Integer> node) {
        LinkedList<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            TreeNode<Integer> rightMost = queue.peek();
            TreeNode<Integer> next = null;
            for (int i = 0; i < queueSize; i++) {
                TreeNode<Integer> curr = queue.poll();
                if (curr == null) {
                    continue;
                }
                curr.setNext(next);
                next = curr;
                queue.offer(curr.getRight());
                queue.offer(curr.getLeft());
            }
            if (rightMost != null) {
                rightMost.setNext(queue.peekLast());
            }
        }
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(1, from(2, from(4), from(5)), from(3, from(6), from(7)))},
                {from(1, from(2, from(4, from(8), from(9)), from(5, from(10), from(11))), from(3, from(6, from(12), from(13)), from(7, from(14), from(15))))}
        };
    }
}