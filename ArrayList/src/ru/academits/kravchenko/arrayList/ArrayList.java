package ru.academits.kravchenko.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private final static int LENGTH_COEFFICIENT = 2;
    private final static int DEFAULT_CAPACITY = 10;

    private T[] elements;
    private int length;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("capacity must be > 0");
        }

        //noinspection unchecked
        elements = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            elements[i] = null;
        }

        length = 0;

        modCount++;
    }

    private void checkIndex(int index, boolean indexCanBeEqualLength) {
        if (indexCanBeEqualLength) {
            if (index > length || index < 0) {
                throw new IndexOutOfBoundsException("index " + index + " out of bounds, it must be in [0, " + length + "]");
            }
        } else {
            if (index >= length || index < 0) {
                throw new IndexOutOfBoundsException("index " + index + " out of bounds, it must be in [0, " + (length - 1) + "]");
            }
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index, false);

        T oldElement = elements[index];
        elements[index] = element;

        modCount++;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
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
        if (length == elements.length) {
            increaseCapacity();
        }

        elements[length] = element;

        length++;

        modCount++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index, true);

        if (length == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, length - index);
        elements[index] = element;

        length++;

        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);

        T oldElement = elements[index];

        if (index < length - 1) {
            System.arraycopy(elements, index + 1, elements, index, length - index - 1);
        }

        length--;

        elements[length] = null;

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
        return Arrays.copyOf(elements, length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(elements, length, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, length);

        if (a.length > length) {
            a[length] = null;
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

        ensureCapacity(length + c.size());

        int i = length;

        for (T t : c) {
            elements[i] = t;
            i++;
        }

        length += c.size();

        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index, true);

        if (c.size() == 0) {
            return false;
        }

        if (index == length) {
            return addAll(c);
        }

        ensureCapacity(length + c.size());

        System.arraycopy(elements, index, elements, index + c.size(), length - index);

        int i = index;

        for (T t : c) {
            elements[i] = t;
            i++;
        }

        length += c.size();

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) { // реализовано удаление без создание нового массива
        if (c.size() == 0) {
            return false;
        }

        boolean isChanged = false;
        int newLength = length;

        for (int i = 0, j = 0; i < length; i++) {
            if (c.contains(elements[i])) {
                newLength--;
            } else {
                elements[j] = elements[i];
                j++;

                modCount++;

                isChanged = true;
            }
        }

        length = newLength;

        if (elements.length > length) {
            trimToSize();
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() == 0) {
            if (length == 0) {
                return false;
            }

            clear();
            return true;
        }

        boolean isChanged = false;
        int newLength = length;

        for (int i = 0, j = 0; i < length; i++) {
            if (c.contains(elements[i])) {
                elements[j] = elements[i];
                j++;

                modCount++;

                isChanged = true;
            } else {
                newLength--;
            }
        }

        length = newLength;

        if (elements.length > length) {
            trimToSize();
        }

        return isChanged;
    }

    private void increaseCapacity() {
        if (length == 0) {
            elements = Arrays.copyOf(elements, DEFAULT_CAPACITY);
        }

        elements = Arrays.copyOf(elements, length * LENGTH_COEFFICIENT);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > length) {
            if (length == 0) {
                //noinspection unchecked
                elements = (T[]) new Object[minCapacity];
            }

            elements = Arrays.copyOf(elements, minCapacity);
        }
    }

    public void trimToSize() {
        if (elements.length > length) {
            if (length == 0) {
                //noinspection unchecked
                elements = (T[]) new Object[0];
            }

            elements = Arrays.copyOf(elements, length);
        }
    }

    public String toString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append(elements[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(elements[length - 1]);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
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