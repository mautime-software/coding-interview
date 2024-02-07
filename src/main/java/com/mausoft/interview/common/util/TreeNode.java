package com.mausoft.interview.common.util;

public class TreeNode<T> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> next;

    public TreeNode(T aValue) {
        value = aValue;
    }

    public TreeNode(T aValue, TreeNode<T> aLeft, TreeNode<T> aRight) {
        value = aValue;
        left = aLeft;
        right = aRight;
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> setLeft(TreeNode<T> aLeft) {
        left = aLeft;
        return this;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public TreeNode<T> setRight(TreeNode<T> aRight) {
        right = aRight;
        return this;
    }

    public TreeNode<T> setValue(T aValue) {
        value = aValue;
        return this;
    }

    public TreeNode<T> getNext() {
        return next;
    }

    public TreeNode<T> setNext(TreeNode<T> aNext) {
        next = aNext;
        return this;
    }

    @Override
    public String toString() {
        return String.format("{%s%s%s%s}", "\"value\": " + value,
                (left != null ? ", \"left\": " + left : ""),
                (right != null ? ", \"right\": " + right : ""),
                (next != null ? ", \"next\": " + next : ""));
    }

    public static <T> TreeNode<T> from(T value, TreeNode<T> left, TreeNode<T> right) {
        return new TreeNode<>(value, left, right);
    }

    public static <T> TreeNode<T> from(T value) {
        return from(value, null, null);
    }

    public static <T> TreeNode<T> from(T... values) {
        return buildNode(values, 0);
    }

    private static <T> TreeNode<T> buildNode(T[] values, int i) {
        if (i >= values.length) {
            return null;
        }
        TreeNode<T> node = from(values[i]);
        node.setLeft(buildNode(values, (2 * i) + 1));
        node.setRight(buildNode(values, (2 * i) + 2));
        return node;
    }
}
