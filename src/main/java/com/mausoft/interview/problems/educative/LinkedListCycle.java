package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

public class LinkedListCycle {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isCycled((ListNode<Integer>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static boolean isCycled(ListNode<Integer> head) {
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head.next();
        while (slow != fast) {
            if (fast == null || fast.next() == null) {
                return false;
            }
            slow = slow.next();
            fast = fast.next().next();
        }
        return true;
    }

    private static Object[][] dataProvider() {
        ListNode<Integer> cycledNode = ListNode.from(3);
        return new Object[][] {
                {buildCycledLinkedList(5, 3)},
                {buildCycledLinkedList(10, 1)},
                {buildCycledLinkedList(10, -1)}
        };
    }

    private static ListNode<Integer> buildCycledLinkedList(int numItems, int cycleAt) {
        ListNode<Integer> head = null;
        ListNode<Integer> next = null;
        ListNode<Integer> cycledNode = null;
        for (int i = 0; i < numItems; i++) {
            ListNode<Integer> node = ListNode.from(i + 1);
            if (head == null) {
                head = node;
                next = node;
            } else  {
                next = next.next(node);
            }

            if (i + 1 == cycleAt) {
                cycledNode = node;
            }
        }
        next.next(cycledNode);
        return head;
    }
}