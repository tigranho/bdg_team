package sam.util;

import java.util.*;

public class CustomArrayList<E> extends AbstractList<E> implements List<E>
{
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private int size;

    private  Object[] elementData;

    public CustomArrayList(int capacity) {
        if(capacity > 0) {
            this.elementData = new Object[capacity];
        }
        else if(capacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }
        else {
            throw new IllegalArgumentException("Wrong capacity: " + capacity);
        }
    }

    public CustomArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
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

        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(E e) {
        add(e, elementData, size);
        return true;
    }

    private void add(E e, Object[] elementData, int s) {
        if(s == elementData.length) {
            elementData = grow();
        }
        elementData[s] =  e;
        size = s + 1;
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData,
                minCapacity + 15);
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    @Override
    public boolean remove(Object o) {
        if(o == null) {
            for(int i = 0; i < size; i++) {
                if(elementData[i] == null) {
                    fastRemove(elementData, i);
                    return true;
                }
            }
        }
        else {
            for(int i = 0; i < size; i++) {
                if(o.equals(elementData[i])) {
                    fastRemove(elementData, i);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public void clear() {
        for(int i = 0; i< size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public  E get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index: " + index);
        }
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Object el = elementData[index];
        fastRemove(elementData, index);
        return (E)el;
    }

    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        if(o == null) {
            for(int i = start; i < end; i++) {
                if(elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for(int i = start; i < end; i++) {
                if(o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    public int lastIndexOfRange(Object o, int start, int end) {
        if(o == null) {
            for(int i = end - 1; i >= start; i++) {
                if(elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for(int i = end - 1; i >= start; i--) {
                if(o.equals(elementData[i])) {
                    return i;
                }
            }
        }

        return -1;
    }
}
