package com.mausoft.interview.common.util;

import java.util.HashSet;
import java.util.Set;

public class ListNode<T> {
    private T value;
    private ListNode<T> next;

    public ListNode(T aValue) {
        value = aValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T aValue) {
        value = aValue;
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
        Set<ListNode<T>> visited = new HashSet<>();
        return "[" + _toString(visited) + "]";
    }

    private String _toString(Set<ListNode<T>> visited) {
        if (visited.contains(this)) {
            return "cycled -> " + value;
        }
        visited.add(this);
        if (next == null) {
            return value.toString();
        }
        return value + ", " + next._toString(visited);
    }
}