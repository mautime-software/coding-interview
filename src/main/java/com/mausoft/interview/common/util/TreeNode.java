package com.mausoft.interview.common.util;

public class TreeNode<T> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;

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

    @Override
    public String toString() {
        return String.format("{%s%s%s}", "\"value\": " + value,
                (left != null ? ", \"left\": " + left : ""),
                (right != null ? ", \"right\": " + right : ""));
    }

    public static <T> TreeNode<T> from(T value, TreeNode<T> left, TreeNode<T> right) {
        return new TreeNode<>(value, left, right);
    }

    public static <T> TreeNode<T> from(T value) {
        return from(value, null, null);
    }
}
