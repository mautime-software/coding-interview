package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.*;
import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/binary-tree-zigzag-level-order-traversal
 *
 * Given a binary tree, return its zigzag level order traversal.
 * The zigzag level order traversal corresponds to traversing nodes from left to right for one level, and then right to left for the next level, and so on,
 * reversing direction after every level.
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> zigzagTraversal((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static List<List<Integer>> zigzagTraversal(TreeNode<Integer> node) {
        List<List<Integer>> zigzag = new ArrayList<>();
        Deque<TreeNode<Integer>> lQueue = new ArrayDeque<>();
        Deque<TreeNode<Integer>> rQueue = new ArrayDeque<>();
        lQueue.offer(node);
        while (!lQueue.isEmpty() || !rQueue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            while (!lQueue.isEmpty()) {
                TreeNode<Integer> curr = lQueue.poll();
                level.addFirst(curr.getValue());
                if (curr.getRight() != null) {
                    rQueue.offer(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    rQueue.offer(curr.getLeft());
                }
            }
            zigzag.add(level);
            level = new LinkedList<>();
            while(!rQueue.isEmpty()) {
                TreeNode<Integer> curr = rQueue.poll();
                level.add(curr.getValue());
                if (curr.getRight() != null) {
                    lQueue.offer(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    lQueue.offer(curr.getLeft());
                }
            }
            zigzag.add(level);
        }
        return zigzag;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(1, from(2, from(4), from(5)), from(3, from(6), from(7)))},
                {from(100, from(50, from(25), from(75)), from(200, from(350), null))},
                {from(0, from(2, from(1, from(5), from(1)), null), from(4, from(3, null, from(6)), from(-1, null, 8)))},
                {from(-9, from(-3, null, from(4, from(-6), null)), from(2, from(4, from(-5), null), from(0)))}
        };
    }
}