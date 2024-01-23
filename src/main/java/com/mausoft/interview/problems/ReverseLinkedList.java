package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/reverse-linked-list
 *
 * Given the head of a singly linked list, reverse the linked list and return its updated head.
 */
public class ReverseLinkedList {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> reverse((ListNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
        System.out.println("================================== RECURSIVE SOLUTION ==================================");
        Function<Object[], Object> recursiveFunction = e -> reverseRec((ListNode<Integer>) e[0]);
        TestExecutor.runTestCases(recursiveFunction, dataProvider());
    }

    public static ListNode<Integer> reverse(ListNode<Integer> head) {
        ListNode<Integer> curr = head;
        ListNode<Integer> prev = null;
        while(curr != null) {
            ListNode<Integer> tmp = curr.next();
            curr.next(prev);
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public static ListNode<Integer> reverseRec(ListNode<Integer> node) {
        if (node == null || node.next() == null) {
            return node;
        }
        ListNode<Integer> reversed = reverseRec(node.next());
        node.next().next(node);
        node.next((ListNode<Integer>) null);
        return reversed;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {ListNode.from(1, 2, 3, 4, 5)},
                {ListNode.from(1, 2, 3, 4, 5, 6)},
                {ListNode.from(3, 2, 1)},
                {ListNode.from(10)},
                {ListNode.from(1, 2)}
        };
    }
}