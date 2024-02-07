package com.mausoft.interview.problems.educative;

import com.mausoft.interview.common.util.ListNode;
import com.mausoft.interview.common.util.TestExecutor;

import java.util.function.Function;

/**
 * https://www.educative.io/courses/grokking-coding-interview-patterns-java/palindrome-linked-list
 *
 * Given the head of a linked list, your task is to check whether the linked list is a palindrome or not.
 * Return TRUE if the linked list is a palindrome; otherwise, return FALSE.
 */
public class PalindromeLinkedList {
    public static void main(String... args) {
        Function<Object[], Object> function = e -> isPalindrome((ListNode<Character>) e[0]);
        TestExecutor.runTestCases(function, dataProvider());
    }
    public static boolean isPalindrome(ListNode<Character> head) {
        ListNode<Character> slow = head;
        ListNode<Character> fast = head;
        while (fast != null && fast.next() != null) {
            slow = slow.next();
            fast = fast.next().next();
        }
        ListNode<Character> rNode = reverse(slow);
        slow = head;
        while (rNode != null) {
            if (slow.getValue() != rNode.getValue()) {
                return false;
            }
            slow = slow.next();
            rNode = rNode.next();
        }
        return true;
    }

    private static ListNode<Character> reverse(ListNode<Character> node) {
        ListNode<Character> curr = node;
        ListNode<Character> prev = null;
        while (curr != null) {
            ListNode<Character> tmp = curr.next();
            curr.next(prev);
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    private static Object[][] dataProvider() {
        return new Object[][]{
                {ListNode.from('2', '4', '6', '4', '2')},
                {ListNode.from('0', '3', '5', '5', '0')},
                {ListNode.from('9', '2', '4', '4', '2', '9')},
                {ListNode.from('5', '4', '7', '9', '4', '5')},
                {ListNode.from('5', '1', '5', '2', '5', '1', '5')}
        };
    }
}