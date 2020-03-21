package ru.academits.kravchenko.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    public T getData(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and < SinglyLinkedList size");
        }

        int i = 0;

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            if (i == index) {
                return current.getData();
            }

            i++;
        }

        return null;
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

        ListItem<T> link = head;

        for (int i = 0; i < index - 1; i++) {
            link = link.getNext();
        }

        ListItem<T> current = new ListItem<>(data, link.getNext());
        link.setNext(current);

        count++;
    }

    public T replace(int index, T data) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= SinglyLinkedList size");
        }

        T result = null;

        int i = 0;

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            if (i == index) {
                result = current.getData();
                current.setData(data);
            }

            i++;
        }

        return result;
    }

    public T deleteFirst() {
        T first = head.getData();

        head = head.getNext();
        count--;

        return first;
    }

    public T delete(int index) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= SinglyLinkedList size");
        }

        if (index == 0) {
            return deleteFirst();
        }

        int i = 1;

        for (ListItem<T> current = head.getNext(), prev = head; current != null; prev = current, current = current.getNext()) {

            if (i == index) {
                prev.setNext(current.getNext());
                count--;
                return current.getData();
            }

            i++;
        }

        return null;
    }

    public boolean deleteFirstEntrance(T value) {
        for (ListItem<T> current = head, prev = null; current != null; prev = current, current = current.getNext()) {
            if (current.getData().equals(value)) {
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
            if (current.getData().equals(value)) {
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
        if (count == 0 || count == 1) {
            return;
        }

        ListItem<T> current = head;
        ListItem<T> linkForCurrent = null;

        while (current != null) {
            ListItem<T> temp = current.getNext();

            current.setNext(linkForCurrent);

            linkForCurrent = current;

            if (temp == null) {
                head = current;
            }

            current = temp;
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        for (ListItem<T> current = head; current != null; current = current.getNext()) {
            result.add(result.getSize(), current.getData());
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
            listToString.append(current.getData().toString());

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