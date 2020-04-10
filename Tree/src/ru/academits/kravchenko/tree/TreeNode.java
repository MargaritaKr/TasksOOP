package ru.academits.kravchenko.tree;

class TreeNode<T> { //
    private T data;
    private TreeNode<T> parent;
    private TreeNode<T> left;
    private TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
    }

    TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    T getData() {
        return data;
    }

    TreeNode<T> getParent() {
        return parent;
    }

    void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public String toString() {
        String dataStr = (data != null) ? data.toString() : "data : null";
        String leftStr = "null";
        String rightStr = "null";

        if (left != null) {
            leftStr = (left.getData() != null) ? left.getData().toString() : "data : null";
        }

        if (right != null) {
            rightStr = (right.getData() != null) ? right.getData().toString() : "data : null";
        }

        return "[ " + dataStr + ", L - " + leftStr + ", R - " + rightStr + " ]";
    }
}