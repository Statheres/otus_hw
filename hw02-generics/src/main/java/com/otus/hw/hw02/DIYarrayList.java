package com.otus.hw.hw02;

import java.util.*;

public class DIYarrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;

    private Object[] data;

    DIYarrayList(int capacity) {
        data = new Object[capacity];
    }

    public DIYarrayList() {
        this(DEFAULT_CAPACITY);
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
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] outputData) {
        if (outputData.length < size) {
            return (T[]) Arrays.copyOf(data, size, outputData.getClass());
        }

        System.arraycopy(data, 0, outputData, 0, size);

        if (outputData.length > size) {
            Arrays.fill(outputData, size, outputData.length, null);
        }

        return outputData;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size + 1);

        data[size] = element;
        ++size;

        return true;
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
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (collection.isEmpty()) {
            return false;
        }

        ensureCapacity(size + collection.size());

        int movedElementsNum = size - index;
        if (movedElementsNum > 0) {
            System.arraycopy(data, index, data, index + collection.size(), movedElementsNum);
        }

        Object[] collectionData = collection.toArray();
        System.arraycopy(collectionData, 0, data, index, collectionData.length);

        size += collectionData.length;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return removeAll(collection, false);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return removeAll(collection, true);
    }

    private boolean removeAll(Collection<?> collection, boolean retain) {
        int indexToRemove = 0;
        for (; ; ++indexToRemove) {
            if (indexToRemove == size) {
                return false;
            }

            if (collection.contains(data[indexToRemove]) != retain) {
                break;
            }
        }

        int indexToAdd = indexToRemove++;
        for (; indexToRemove < size; ++indexToRemove) {
            if (collection.contains(data[indexToRemove]) == retain) {
                data[indexToAdd++] = data[indexToRemove];
            }
        }

        Arrays.fill(data, indexToAdd, size, null);

        size = indexToAdd;

        return true;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        E oldElement = (E) data[index];
        data[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        ++size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        E oldElement = (E) data[index];

        int newSize = size - 1;
        if (index < newSize) {
            System.arraycopy(data, index + 1, data, index, newSize - index);
        }

        size = newSize;
        data[size] = null;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity > data.length) {
            data = Arrays.copyOf(data, newCapacity);
        }
    }
}
