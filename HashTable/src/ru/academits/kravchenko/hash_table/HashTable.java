package ru.academits.kravchenko.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY = 20;

    private final ArrayList<T>[] elements;
    private int count;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        elements = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }

        //noinspection unchecked
        elements = new ArrayList[capacity];
    }

    private int getArrayIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % elements.length);
    }

    @Override
    public boolean add(T t) {
        int arrayIndex = getArrayIndex(t);

        if (elements[arrayIndex] == null) {
            elements[arrayIndex] = new ArrayList<>();
        }

        elements[arrayIndex].add(t);

        count++;

        modCount++;

        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean contains(Object o) {
        int arrayIndex = getArrayIndex(o);

        return (elements[arrayIndex] != null) && elements[arrayIndex].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[count];

        int i = 0;

        for (T t : this) {
            array[i] = t;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < count) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), count, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, count);

        if (a.length > count) {
            a[count] = null;
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        int arrayIndex = getArrayIndex(o);

        if (elements[arrayIndex] == null) {
            return false;
        }

        if (elements[arrayIndex].remove(o)) {
            count--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        for (T element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0 || count == 0) {
            return false;
        }

        boolean isChanged = false;

        for (Object o : c) {
            while (remove(o)) {

                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (count == 0) {
            return false;
        }

        if (c.size() == 0) {
            clear();

            return true;
        }

        int newCount = 0;

        for (ArrayList<T> element : elements) {
            if (element != null) {
                element.retainAll(c);

                newCount += element.size();
            }
        }

        if (count == newCount) {
            return false;
        }

        count = newCount;

        modCount++;

        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);

        count = 0;

        modCount++;
    }

    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        int i = 0;

        for (ArrayList<T> element : elements) {
            if (element != null && element.size() > 0) {
                for (T t : element) {
                    stringBuilder.append(t);

                    i++;

                    if (i != count) {
                        stringBuilder.append(", ");
                    } else {
                        stringBuilder.append("]");
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

    public String toTable() {
        if (count == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{").append(System.lineSeparator());

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].size() > 0) {
                stringBuilder.append("Key - ").append(i).append(": [");

                for (int j = 0; j < elements[i].size(); j++) {
                    stringBuilder.append(elements[i].get(j));

                    if (j != elements[i].size() - 1) {
                        stringBuilder.append(", ");
                    }
                }

                stringBuilder.append("]").append(System.lineSeparator());
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    private class HashTableIterator implements Iterator<T> {
        private final int currentModCount = modCount;
        private int currentIndex = -1;
        private int listIndex = 0;
        private int inListIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("HashTable has not such elements");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Modification of the HashTable has changed");
            }

            while (true) {
                if (elements[listIndex] == null || inListIndex == elements[listIndex].size()) {
                    listIndex++;
                    inListIndex = 0;

                    continue;
                }

                int index = inListIndex;

                inListIndex++;
                currentIndex++;

                return elements[listIndex].get(index);
            }
        }
    }
}