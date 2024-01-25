package com.mausoft.interview.problems;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * Given a singly linked list with n nodes and two positions, left and right, the objective is to reverse the nodes of the list from left to right.
 * Return the modified list.
 */
public class ReverseLinkedListII {
    public static void main(String... args) {
        Function<Object[], Object> function = e-> reverse((ListNode<Integer>) e[0], (int) e[1], (int) e[2]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static ListNode<Integer> reverse(ListNode<Integer> head, int a, int b) {
        ListNode<Integer> left = head;
        ListNode<Integer> pLeft = null;
        for (int i = 1; i < a; i++) {
            pLeft = left;
            left = left.next();
        }
        ListNode<Integer> right = head;
        ListNode<Integer> pRight = null;
        for (int i = 1; i < b; i++) {
            right = right.next();
            pRight = right.next();
        }
        ListNode<Integer> reversed = reverse(left, right.next());
        if (pLeft != null) {
            pLeft.next(reversed);
        } else {
            head = reversed;
        }
        left.next(pRight);
        return head;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> node, ListNode<Integer> stop) {
        if (node.next() == stop) {
            return node;
        }
        ListNode<Integer> reversed = reverse(node.next(), stop);
        node.next().next(node);
        node.next((ListNode<Integer>) null);
        return reversed;
    }

    private static Object[][] dataProvider() {
        return new Object[][] {
                {ListNode.from(6, 8, 7), 1, 2},
                {ListNode.from(9, 0, 8, 2), 2, 4},
                {ListNode.from(1, 2, 3, 4, 5), 1, 5},
                {ListNode.from(7, 4, 6, 1, 5, 8), 2, 5},
                {ListNode.from(1, 2, 3, 4, 5, 6, 7), 1, 5},
                {ListNode.from(6, 9, 3, 10, 7, 4, 6), 3, 6},
                {ListNode.from(6, 9, 3, 4), 2, 4},
                {ListNode.from(6, 2, 3, 6, 9), 1, 3},
                {ListNode.from(6, 2), 1, 2}
        };
    }
}