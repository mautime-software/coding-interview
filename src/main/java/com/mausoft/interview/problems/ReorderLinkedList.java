package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/reorder-list
 *
 * Given the head of a singly linked list, reorder the list as if it were folded on itself. For example, if the list is represented as follows:
 * L0 -> L1 -> L2 -> ... -> Ln-2 -> Ln-1 -> Ln
 * L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln2 -> ...
 *
 * 9 -> 0 -> 8 -> 2
 * 9 -> 2 -> 0 -> 8
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 * 1 -> 5 -> 2 -> 4 -> 3
 *
 * 7 -> 4 -> 6 -> 1 -> 5 -> 8
 * 7 -> 8 -> 1 -> 5 -> 6 -> 1
 */
public class ReorderLinkedList {
    private static ListNode<Integer> slow;

    public static void main(String... args) {
        Function<Object[], Object> function = e -> reorder((ListNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static ListNode<Integer> reorder(ListNode<Integer> head) {
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;
        ListNode<Integer> prev = null;
        while(fast != null && fast.next() != null) {
            prev = slow;
            slow = slow.next();
            fast = fast.next().next();
        }
        ListNode<Integer> rNode = reverse(slow);
        ListNode<Integer> lNode = head;
        while(rNode != null) {
            ListNode<Integer> lTempNode = lNode.next();
            ListNode<Integer> rTempNode = rNode.next();
            lNode.next(rNode);
            lNode.next().next(lTempNode);
            lNode = lTempNode;
            rNode = rTempNode;
        }
        if (lNode != null) {
            lNode.next((ListNode<Integer>) null);
        }
        return head;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> node) {
        if (node == null || node.next() == null) {
            return node;
        }
        ListNode<Integer> reversed = reverse(node.next());
        node.next().next(node);
        node.next((ListNode<Integer>) null);
        return reversed;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {ListNode.from(9, 0, 8, 2)},
                {ListNode.from(1, 2, 3, 4, 5)},
                {ListNode.from(7, 4, 6, 1, 5, 8)},
                {ListNode.from(1, 1, 2, 2, 3, -1, 10, 12)},
                {ListNode.from(10, 20, -22, 21, -12)},
                {ListNode.from(1, 1, 1)},
                {ListNode.from(-2, -5, -6, 0, -1, -4)},
                {ListNode.from(3, 1, 5, 7, -4, -2, -1, -6)}
        };
    }
}