package ru.academits.kravchenko.tree;

/*
• Вставка
• Поиск узла
• Удаление первого вхождения узла по значению
• Получение числа элементов
• Обходы в ширину и глубину. Обход в глубину двумя вариантами – с рекурсией и без
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private Comparator<? super T> comparator;
    private int size = 0;

    public Tree() {
        comparator = null;
    }

    public Tree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public Comparator<? super T> comparator() {
        return comparator;
    }

    private int compare(Object data1, Object data2) {
        if (data1 == null && data2 == null) {
            return 0;
        } else if (data1 == null) {
            return -1;
        } else if (data2 == null) {
            return 1;
        } else {
            if (comparator != null) {
                //noinspection unchecked
                return comparator.compare((T) data1, (T) data2);
            }
            //noinspection unchecked
            return ((Comparable<? super T>) data1).compareTo((T) data2);
        }
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        TreeNode<T> current = root;

        while (true) {
            if (compare(data, current.getData()) < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                    continue;
                }

                current.setLeft(new TreeNode<>(data, current));
                break;
            }

            if (current.getRight() != null) {
                current = current.getRight();
                continue;
            }

            current.setRight(new TreeNode<>(data, current));
            break;
        }

        size++;
    }

    public void addList(T[] dataList) {
        if (dataList != null) {
            for (T data : dataList) {
                add(data);
            }
        }
    }

    private TreeNode<T> containing(T data) {
        TreeNode<T> current = root;

        while (current != null) {
            int compare = compare(data, current.getData());

            if (compare == 0) {
                return current;
            }

            if (compare < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                    continue;
                }

                return null;
            }

            if (current.getRight() != null) {
                current = current.getRight();
                continue;
            }

            return null;
        }

        return null;
    }

    public boolean contains(T data) {
        return containing(data) != null;
    }

    public boolean remove(T data) {
        TreeNode<T> containingData = containing(data);

        if (containingData == null) {
            return false;
        }

        if (containingData == root) {                                                          // удаляем корень
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getLeft() == null || root.getRight() == null) {
                root = (root.getLeft() != null) ? root.getLeft() : root.getRight();
                root.setParent(null);
            } else {
                TreeNode<T> leftHalfMax = root.getLeft();

                while (leftHalfMax != null) {
                    if (leftHalfMax.getRight() != null) {
                        leftHalfMax = leftHalfMax.getRight();
                    } else {
                        leftHalfMax.setRight(root.getRight());
                        break;
                    }
                }

                root = root.getLeft();
                root.setParent(null);
            }

            size--;
            return true;
        }

        TreeNode<T> parent = containingData.getParent();
        int compareParent = compare(data, parent.getData());

        if (containingData.getLeft() == null && containingData.getRight() == null) {            // удаляем лист
            if (compareParent < 0) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (containingData.getLeft() == null || containingData.getRight() == null) {     // удаляем узел с одним ребенком
            TreeNode<T> child = (containingData.getLeft() != null) ? containingData.getLeft() : containingData.getRight();

            if (compareParent < 0) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }

            child.setParent(parent);
        } else {                                                                               // удаляем узел с двумя детьми
            TreeNode<T> nextMin = containingData.getRight();

            while (nextMin.getLeft() != null) {
                nextMin = nextMin.getLeft();
            }

            if (nextMin.getRight() != null) {
                nextMin.getParent().setLeft(nextMin.getRight());
            } else {
                nextMin.getParent().setLeft(null);
            }

            nextMin.setLeft(containingData.getLeft());
            nextMin.setRight(containingData.getRight());

            if (compareParent < 0) {
                containingData.getParent().setLeft(nextMin);
            } else {
                containingData.getParent().setRight(nextMin);
            }
        }

        size--;
        return true;
    }

    public void wideVisit(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        ArrayList<TreeNode<T>> queue = new ArrayList<>();
        queue.add(root);

        while (queue.size() > 0) {
            TreeNode<T> current = queue.remove(0);

            consumer.accept(current.getData());

            TreeNode<T> left = current.getLeft();
            TreeNode<T> right = current.getRight();

            if (left != null) {
                queue.add(left);
            }

            if (right != null) {
                queue.add(right);
            }
        }
    }

    public void inDepthVisit(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode<T> current = stack.pop();

            consumer.accept(current.getData());

            TreeNode<T> left = current.getLeft();
            TreeNode<T> right = current.getRight();

            if (right != null) {
                stack.push(right);
            }

            if (left != null) {
                stack.push(left);
            }
        }
    }

    public void recursiveVisit(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        recursiveVisit(consumer, root);
    }

    private void recursiveVisit(Consumer<T> consumer, TreeNode<T> node) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) recursiveVisit(consumer, node.getLeft());
        if (node.getRight() != null) recursiveVisit(consumer, node.getRight());
    }

    public String toString() {
        if (root == null) {
            return "{ }";
        }

        StringBuilder treeToString = new StringBuilder("{ ");

        wideVisit(x -> treeToString.append(x).append(", "));

        treeToString.replace(treeToString.lastIndexOf(", "), treeToString.lastIndexOf(", ") + 2, " }");

        return treeToString.toString();
    }

    public String printTree() {       // фактически это обход в ширину, но так как тут печатается сам узел, а не данные - реализовано отдельно
        if (root == null) {
            return "{ }";
        }

        StringBuilder treeToString = new StringBuilder("{ ");

        ArrayList<TreeNode<T>> queue = new ArrayList<>();
        queue.add(root);

        while (queue.size() > 0) {
            treeToString.append(System.lineSeparator());

            TreeNode<T> current = queue.get(0);

            treeToString.append(current);

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }

            queue.remove(0);
        }

        treeToString.append(System.lineSeparator()).append(" }");

        return treeToString.toString();
    }
}