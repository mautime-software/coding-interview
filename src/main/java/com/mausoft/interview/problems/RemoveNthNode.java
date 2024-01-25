package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/remove-nth-node-from-end-of-list
 *
 * Given a singly linked list, remove the nth node from the end of the list and return its head.
 */
public class RemoveNthNode {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> removeNthNodeOptimized((ListNode<Integer>) e[0], (Integer) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {ListNode.from(23,28,10,5,67,39,70,28), 2},
                {ListNode.from(34,53,6,95,38,28,17,63,16,76), 3},
                {ListNode.from(288,224,275,390,4,383,330,60,193), 4},
                {ListNode.from(1,2,3,4,5,6,7,8,9), 1},
                {ListNode.from(69,8,49,106,116,112), 6}
        };
    }
    public static ListNode<Integer> removeNthNode(ListNode<Integer> head, int n) {
        ListNode<Integer> rNode = head;
        for (int i = 0; i < n; i++) {
            rNode = rNode.next();
        }

        if (rNode == null) {
            return head.next();
        }
        ListNode<Integer> lNode = head;
        while(rNode.next() != null) {
            lNode = lNode.next();
            rNode = rNode.next();
        }
        lNode.next(lNode.next().next());
        return head;
    }

    public static ListNode<Integer> removeNthNodeOptimized(ListNode<Integer> head, int k) {
        int count = 0;
        ListNode<Integer> node = head;
        ListNode<Integer> end = null;
        ListNode<Integer> prev = null;
        while (node != null) {
            count++;
            if (end != null) {
                prev = end;
                end = end.next();
            }
            if (count == k) {
                end = head;
            }
            node = node.next();
        }
        if (prev == null) {
            return head.next();
        }
        prev.next(end.next());
        return head;
    }
}