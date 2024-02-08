package com.mausoft.interview.problems.interviewbit;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

import static com.mausoft.interview.common.util.ListNode.*;

/**
 * https://www.interviewbit.com/problems/add-two-numbers-as-lists/
 *
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * 342 + 465 = 807
 *
 * Make sure there are no trailing zeros in the output list
 * So, 7 -> 0 -> 8 -> 0 is not a valid response even though the value is still 807.
 */
public class AddTwoNumbersAsLists {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> sum((ListNode<Integer>) e[0], (ListNode<Integer>) e[1]);
        TestExecutor.runTestCases(function, dataProvider());
    }

    public static ListNode<Integer> sum(ListNode<Integer> a, ListNode<Integer> b) {
        int carrier = 0;
        ListNode<Integer> node = null;
        ListNode<Integer> head = null;
        while (a != null && b != null) {
            int sum = a.getValue() + b.getValue() + carrier;
            if (sum >= 10) {
                carrier = 1;
                sum = sum % 10;
            } else {
                carrier = 0;
            }
            ListNode<Integer> sumNode = from(sum);
            if (node != null) {
                node.next(sumNode);
            }
            node = sumNode;
            if (head == null) {
                head = node;
            }
            a = a.next();
            b = b.next();
        }
        if (a != null) {
            ListNode<Integer> sumNode = sum(a, carrier);
            node.next(sumNode);
        }
        if (b != null) {
            ListNode<Integer> sumNode = sum(b, carrier);
            node.next(sumNode);
        }
        return head;
    }

    private static ListNode<Integer> sum(ListNode<Integer> node, int carrier) {
        ListNode<Integer> tail = null;
        ListNode<Integer> head = null;
        while (node != null) {
            int sum = node.getValue() + carrier;
            if (sum >= 10) {
                carrier = 1;
                sum = sum % 10;
            } else {
                carrier = 0;
            }
            ListNode<Integer> sumNode = from(sum);
            if (tail != null) {
                tail.next(sumNode);
            }
            tail = sumNode;
            if (head == null) {
                head = tail;
            }
            node = node.next();
        }
        if (carrier != 0) {
            tail.next(from(carrier));
        }
        return head;
    }
    private static Object[][] dataProvider() {
        return new Object[][] {
                {from(2, 4, 3), from(5, 6, 4)},
                {from(1), from(9, 9, 9)},
                {from(9, 9, 1), from(1)}
        };
    }
}