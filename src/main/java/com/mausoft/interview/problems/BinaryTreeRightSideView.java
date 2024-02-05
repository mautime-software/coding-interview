package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/binary-tree-right-side-view
 *
 * You are given a root of a binary tree that has n number of nodes. You have to return the right-side view in the form of a list.
 *
 * A right-side view of a binary tree is the data of the nodes that are visible when the tree is viewed from the right side.
 */
public class BinaryTreeRightSideView {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> rightSideView((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static int[] rightSideView(TreeNode<Integer> node) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        buildRightView(node, 0, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void buildRightView(TreeNode<Integer> node, int level, Map<Integer, Integer> levelMap, List<Integer> results) {
        if (node == null) {
            return;
        }
        if (level == results.size()) {
            levelMap.put(level, levelMap.getOrDefault(level, 0) + 1);
            results.add(node.getValue());
        }
        buildRightView(node.getRight(), level + 1, levelMap, results);
        buildRightView(node.getLeft(), level + 1, levelMap, results);
    }

    public static void buildRightView(TreeNode<Integer> node, int level, List<Integer> results) { //without extra space (map)
        if (node == null) {
            return;
        }
        if (level == results.size()) {
            results.add(node.getValue());
        }
        buildRightView(node.getRight(), level + 1, results);
        buildRightView(node.getLeft(), level + 1, results);
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(1, from(2, from(4), from(5, from(8), from(9))), from(3, from(6), from(7)))},
                {from(1, from(2), from(3, from(4), from(5)))},
                {from(1, from(2, from(3, from(4), null), null), null)},
                {from(1, from(2, from(4, from(8), null), from(5)), from(3, from(6), from(7)))},
                {from(1, from(2, from(3), from(4)), null)}
        };
    }
}