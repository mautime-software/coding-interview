package com.mausoft.interview.common.util;

public class ListNode<T> {
    private T value;
    private ListNode<T> next;

    public ListNode(T aValue) {
        value = aValue;
    }

    public T getValue() {
        return value;
    }

    public ListNode<T> next() {
        return next;
    }

    public ListNode<T> next(T aValue) {
        next = new ListNode<>(aValue);
        return next;
    }

    public ListNode<T> next(ListNode<T> aNext) {
        next = aNext;
        return next;
    }

    public static <T> ListNode<T> from(T... values) {
        ListNode<T> head = new ListNode<>(values[0]);
        ListNode<T> aux = head;
        for (int i = 1; i < values.length; i++) {
            aux = aux.next(values[i]);
        }
        return head;
    }

    public String toString() {
        return "[" + _toString() + "]";
    }

    private String _toString() {
        if (next == null) {
            return value.toString();
        }
        return value + ", " + next._toString();
    }
}