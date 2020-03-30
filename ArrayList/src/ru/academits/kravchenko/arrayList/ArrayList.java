package ru.academits.kravchenko.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private final static int LENGTH_COEFFICIENT = 2;

    private T[] elements;
    private int length;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        elements = (T[]) new Object[10];
    }

    public ArrayList(int capacity) {
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
        length = 0;

        trimToSize();

        modCount++;
    }

    @Override
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < ArrayList size");
        }

        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < ArrayList size");
        }

        T oldElement = elements[index];
        elements[index] = element;

        modCount++;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < length; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }

            return -1;
        }

        for (int i = 0; i < length; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;

        if (o == null) {
            for (int i = 0; i < length; i++) {
                if (elements[i] == null) {
                    lastIndex = i;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (o.equals(elements[i])) {
                    lastIndex = i;
                }
            }
        }

        return lastIndex;
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
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= ArrayList size");
        }

        if (length == elements.length) {
            increaseCapacity();
        }

        if (index == length) {
            elements[length] = element;
        } else {
            System.arraycopy(elements, index, elements, index + 1, length - index);
            elements[index] = element;
        }

        length++;

        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < ArrayList size");
        }

        T oldElement = elements[index];

        if (index < length - 1) {
            System.arraycopy(elements, index + 1, elements, index, length - index - 1);

        }

        length--;

        elements[length] = null;

        modCount++;

        if (elements.length > length) {
            trimToSize();
        }

        return oldElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        System.arraycopy(elements, index + 1, elements, index, length - index - 1);

        length--;

        modCount++;

        if (elements.length > length) {
            trimToSize();
        }

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
        //noinspection unchecked
        T1[] elementsNew = (T1[]) elements;

        System.arraycopy(elementsNew, 0, a, 0, Math.min(a.length, length));

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

        for (T t : c) {
            add(t);
            modCount++;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("index must be > 0 and <= ArrayList size");
        }

        if (c.size() == 0) {
            return false;
        }

        if (index == length) {
            return addAll(c);
        }

        if (length + c.size() >= elements.length) {
            ensureCapacity(length + c.size());
        }

        System.arraycopy(elements, index, elements, index + c.size(), length - index);

        int i = 0;

        for (T t : c) {
            elements[index + i] = t;
            i++;
            modCount++;
        }

        length += c.size();

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
            boolean elementFound = false;

            for (Object o : c) {
                if (Objects.equals(elements[i], o)) {
                    newLength--;
                    elementFound = true;
                    break;
                }
            }

            if (!elementFound) {
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
            boolean elementFound = false;

            for (Object o : c) {
                if (Objects.equals(elements[i], o)) {
                    elementFound = true;
                    break;
                }
            }

            if (elementFound) {
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
        elements = Arrays.copyOf(elements, length * LENGTH_COEFFICIENT);
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= length) {
            return;
        }

        elements = Arrays.copyOf(elements, minCapacity);
    }

    public void trimToSize() {
        elements = Arrays.copyOf(elements, length);
    }

    public String toString() {
        if (length == 0) {
            return "[ ]";
        }

        StringBuilder listToString = new StringBuilder("[");

        for (int i = 0; i < length; i++) {
            if (elements[i] == null) {
                listToString.append("null");
            } else {
                listToString.append(elements[i]);
            }

            if (i == length - 1) {
                listToString.append("]");
                return listToString.toString();
            }

            listToString.append(", ");
        }

        return listToString.toString();
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
            if (currentIndex + 1 == length) {
                throw new NoSuchElementException("Modification of the ArrayList has changed");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("ArrayList has not such elements");
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