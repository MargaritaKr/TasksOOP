package ru.academits.kravchenko.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private final static int LENGTH_COEFFICIENT = 2;
    private final static int DEFAULT_CAPACITY = 10;

    private T[] elements;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 0");
        }

        //noinspection unchecked
        elements = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;

        modCount++;
    }

    private void checkIndexCanBeEqualToLength(int index){
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds, it must be in [0, " + size + "]");
        }
    }

    private void checkIndexCanNotBeEqualToLength(int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("index " + index + " out of bounds, it must be in [0, " + (size - 1) + "]");
        }
    }

    @Override
    public T get(int index) {
        checkIndexCanNotBeEqualToLength(index);

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndexCanNotBeEqualToLength(index);

        T oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(T element) {
        if (size == elements.length) {
            increaseCapacity();
        }

        elements[size] = element;

        size++;

        modCount++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndexCanBeEqualToLength(index);

        if (size == elements.length) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = element;

        size++;

        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndexCanNotBeEqualToLength(index);

        T oldElement = elements[index];

        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }

        size--;

        elements[size] = null;

        modCount++;

        return oldElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(elements, size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
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

        ensureCapacity(size + c.size());

        int i = size;

        for (T t : c) {
            elements[i] = t;
            i++;
        }

        size += c.size();

        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexCanBeEqualToLength(index);

        if (c.size() == 0) {
            return false;
        }

        if (index == size) {
            return addAll(c);
        }

        ensureCapacity(size + c.size());

        System.arraycopy(elements, index, elements, index + c.size(), size - index);

        int i = index;

        for (T t : c) {
            elements[i] = t;
            i++;
        }

        size += c.size();

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) { // реализовано удаление без создание нового массива
        if (c.size() == 0) {
            return false;
        }

        boolean isChanged = false;
        int newLength = size;

        for (int i = 0, j = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                newLength--;
            } else {
                elements[j] = elements[i];
                j++;

                modCount++;

                isChanged = true;
            }
        }

        size = newLength;

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            if (size == 0) {
                return false;
            }

            clear();
            return true;
        }

        boolean isChanged = false;
        int newLength = size;

        for (int i = 0, j = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                elements[j] = elements[i];
                j++;

                modCount++;

                isChanged = true;
            } else {
                newLength--;
            }
        }

        size = newLength;

        return isChanged;
    }

    private void increaseCapacity() {
        elements = (size == 0)
                ? Arrays.copyOf(elements, DEFAULT_CAPACITY)
                : Arrays.copyOf(elements, size * LENGTH_COEFFICIENT);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            //noinspection unchecked
            elements = (size == 0)
                    ? (T[]) new Object[minCapacity]
                    : Arrays.copyOf(elements, minCapacity);
        }
    }

    public void trimToSize() {
        //noinspection unchecked
        elements = (size == 0)
                ? (T[]) new Object[0]
                : Arrays.copyOf(elements, size);
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(elements[size - 1]);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private class ArrayListIterator implements Iterator<T> {
        private final int currentModCount = modCount;
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("ArrayList has not such elements");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Modification of the ArrayList has changed");
            }

            currentIndex++;
            return elements[currentIndex];
        }
    }

    @Override
    public ListIterator<T> listIterator() { // НЕ НАДО
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {  // НЕ НАДО
        //noinspection ConstantConditions
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {   // НЕ НАДО
        //noinspection ConstantConditions
        return null;
    }
}