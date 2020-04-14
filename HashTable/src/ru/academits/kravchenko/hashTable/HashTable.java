package ru.academits.kravchenko.hashTable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final static int DEFAULT_CAPACITY = 20;

    private ArrayList<T>[] elements;
    private int count;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        elements = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IndexOutOfBoundsException("capacity must be > 0");
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

    private int getPlaceInArray(Object o) {
        ArrayList<T> array = elements[getArrayIndex(o)];

        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                if (Objects.equals(o, array.get(i))) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return getPlaceInArray(o) >= 0;
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
        if (elements[getArrayIndex(o)] == null) {
            return false;
        }

        modCount++;

        return elements[getArrayIndex(o)].remove(o);
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

        for (T t : c) {
            add(t);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isChanged = false;

        for (Object o : c) {
            while (remove(o)){
                count--;

                isChanged = true;
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isChanged = false;

        for (ArrayList<T> list : elements) {
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); ) {
                    T current = list.get(i);

                    if (!c.contains(current)) {
                        list.remove(current);

                        count--;

                        isChanged = true;
                    } else {
                        i++;
                    }
                }
            }
        }

        if (isChanged) {
            modCount++;
        }

        return isChanged;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);

        count = 0;

        modCount++;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public String toTable() {
        if (count == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{" + System.lineSeparator());

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
        private int currentIndex = -1;
        private int currentModCount = modCount;

        private int currentArray = 0;
        private int currentArrayIndex = 0;

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
                if (elements[currentArray] == null || currentArrayIndex == elements[currentArray].size()) {
                    currentArray++;
                    currentArrayIndex = 0;

                    continue;
                }

                int index = currentArrayIndex;

                currentArrayIndex++;
                currentIndex++;

                return elements[currentArray].get(index);
            }
        }
    }
}