package ru.academits.kravchenko.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    private ListItem<T> getLink(int index) {
        ListItem<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current;
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("SinglyLinkedList has not elements");
        }

        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and < SinglyLinkedList size");
        }

        return getLink(index).getData();
    }

    public T setData(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= SinglyLinkedList size");
        }

        ListItem<T> link = getLink(index);

        T result = link.getData();
        link.setData(data);

        return result;
    }

    public void addToStart(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void add(int index, T data) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= SinglyLinkedList size");
        }

        if (index == 0) {
            addToStart(data);
            return;
        }

        ListItem<T> link = getLink(index - 1);

        ListItem<T> current = new ListItem<>(data, link.getNext());

        link.setNext(current);

        count++;
    }

    public T deleteFirst() {
        if (count == 0) {
            throw new NoSuchElementException("SinglyLinkedList has not elements");
        }

        T first = head.getData();

        head = head.getNext();
        count--;

        return first;
    }

    public T delete(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= SinglyLinkedList size");
        }

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> prev = getLink(index - 1);
        ListItem<T> current = prev.getNext();

        prev.setNext(current.getNext());

        count--;

        return current.getData();
    }

    public boolean deleteFirstEntrance(T value) {
        for (ListItem<T> current = head, prev = null; current != null; prev = current, current = current.getNext()) {
            if (Objects.equals(value, current.getData())) {
                if (prev == null) {
                    head = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }

                count--;
                return true;
            }
        }

        return false;
    }

    public boolean deleteAllEntrances(T value) {
        boolean isDelete = false;

        ListItem<T> current = head;
        ListItem<T> prev = null;

        while (current != null) {
            if (Objects.equals(value, current.getData())) {
                if (prev == null) {
                    head = current.getNext();

                    prev = null;
                    current = head;
                } else {
                    prev.setNext(current.getNext());

                    current = current.getNext();
                }

                count--;

                isDelete = true;
            } else {
                prev = current;
                current = current.getNext();
            }
        }

        return isDelete;
    }

    public void flip() {
        if (count <= 1) {
            return;
        }

        ListItem<T> current = head;
        ListItem<T> prev = null;

        while (current != null) {
            ListItem<T> next = current.getNext();

            current.setNext(prev);

            prev = current;

            if (next == null) {
                head = current;
            }

            current = next;
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        if (count == 0) {
            return result;
        }

        result.head = new ListItem<>(head.getData());

        for (ListItem<T> current = head.getNext(), currentInResult = result.head; current != null;
             current = current.getNext(), currentInResult = currentInResult.getNext()) {

            ListItem<T> nextInResult = new ListItem<>(current.getData());

            currentInResult.setNext(nextInResult);
        }

        result.count = count;

        return result;
    }

    public String toString() {
        if (count == 0) {
            return "{ }";
        }

        StringBuilder listToString = new StringBuilder("{ ");

        int i = 0;

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            listToString.append(current.getData());

            if (i == getSize() - 1) {
                listToString.append(" }");
                return listToString.toString();
            }

            listToString.append(", ");
            i++;
        }

        return listToString.toString();
    }
}