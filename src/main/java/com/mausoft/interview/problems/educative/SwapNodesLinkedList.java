package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * Given the linked list and an integer, k, return the head of the linked list after swapping the values of the kth node from the beginning
 * and the kth node from the end of the linked list.
 */
public class SwapNodesLinkedList {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> swapNodes((ListNode<Integer>) e[0], (int) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
        System.out.println("================================ SWAP VALUES (ONLY) ================================");
        Function<Object[], Object> swapValuesFunction = e -> swapValues((ListNode<Integer>) e[0], (int) e[1]);
        TestExecutor.runTestCases(swapValuesFunction, dataProvider());
    }

    public static ListNode<Integer> swapNodes(ListNode<Integer> head, int k) {
        ListNode<Integer> right = head;
        for (int i = 0; i < k; i++) {
            right = right.next();
        }
        ListNode<Integer> end = head;
        ListNode<Integer> pEnd = null;
        while (right != null) {
            pEnd = end;
            end = end.next();
            right = right.next();
        }
        ListNode<Integer> start = head;
        ListNode<Integer> pStart = null;
        for (int i = 1; i < k; i++) {
            pStart = start;
            start = start.next();
        }
        if (start == end) {
            return head;
        }
        if (pStart == null) {
            head = end;
        } else {
            pStart.next(end);
        }
        ListNode<Integer> tmpEndNext = end.next();
        if (end == start.next()) {
            end.next(start);
            start.next(tmpEndNext);
        } else {
            end.next(start.next());
            pEnd.next(start);
            start.next(tmpEndNext);
        }
        return head;
    }

    public static ListNode<Integer> swapValues(ListNode<Integer> head, int k) {
        int count = 0;
        ListNode<Integer> node = head;
        ListNode<Integer> start = null;
        ListNode<Integer> end = null;
        while (node != null) {
            count++;
            if (end != null) {
                end = end.next();
            }
            if (count == k) {
                start = node;
                end = head;
            }
            node = node.next();
        }
        swap(start, end);
        return head;
    }

    private static void swap(ListNode<Integer> a, ListNode<Integer> b) {
        Integer tmp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(tmp);
    }

    private static Object[][]  dataProvider() {
        return new Object[][] {
                {ListNode.from(6, 8, 7), 1},
                {ListNode.from(9, 0, 8, 2), 2},
                {ListNode.from(1, 2, 3, 4, 5), 3},
                {ListNode.from(7, 4, 6, 1, 5, 8), 5},
                {ListNode.from(1, 2, 3, 4, 5, 6, 7), 2},
                {ListNode.from(6, 9, 3, 10, 7, 4, 6), 3},
                {ListNode.from(6, 9, 3, 4), 2},
                {ListNode.from(6, 2, 3, 6, 9), 3},
                {ListNode.from(6, 2), 1}
        };
    }
}