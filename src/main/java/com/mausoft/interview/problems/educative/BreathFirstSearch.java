package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.TestExecutor;
import com.mausoft.interview.common.util.TreeNode;

import java.util.*;
import java.util.function.Function;

import static com.mausoft.interview.common.util.TreeNode.*;

public class BreathFirstSearch {
    public static void main(String... args) {
        System.out.println("============================================= TOP DOWN =============================================");
        Function<Object[], Object> flattenTopDown = e -> flattenTopDown((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(flattenTopDown, dataProvider());
        System.out.println("============================================= BOTTOM UP =============================================");
        Function<Object[], Object> flattenBottomUp = e -> flattenBottomUp((TreeNode<Integer>) e[0]);
        TestExecutor.runTestCases(flattenBottomUp, dataProvider());
    }

    //Print all nodes of a perfect binary tree in a top-down manner
    public static List<Integer> flattenTopDown(TreeNode<Integer> node) {
        Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
        queue.offer(node);
        List<Integer> results = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode<Integer> curr = queue.poll();
            results.add(curr.getValue());
            if (curr.getLeft() != null) {
                queue.offer(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.offer(curr.getRight());
            }
        }
        return results;
    }

    public static List<Integer> flattenBottomUp(TreeNode<Integer> node) {
        Deque<TreeNode<Integer>> queue = new ArrayDeque<>();
        LinkedList<Integer> stack = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode<Integer> curr = queue.poll();
            stack.push(curr.getValue());
            if (curr.getRight() != null) {
                queue.offer(curr.getRight());
            }
            if (curr.getLeft() != null) {
                queue.offer(curr.getLeft());
            }
        }
        List<Integer> results = new ArrayList<>();
        while (!stack.isEmpty()) {
            results.add(stack.pop());
        }
        return results;
    }
    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(0, from(1, from(3), from(4, from(7), from(8))), from(2, from(5), from(6, from(9), from(10))))}
        };
    }
}