package Customs;

import java.util.*;

public class CustomArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_DATA = {};

    private Object[] data;

    private int size;

    public CustomArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public CustomArrayList(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity" + capacity);
        }
        size = 0;
        if(capacity > 0) {
            data = new Object[capacity];
        }
        else {
            data = EMPTY_DATA;
        }
    }

    public CustomArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                data = a;
            } else {
                data = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            data = EMPTY_DATA;
        }
    }

    public void trimToSize() {
        if(size < data.length) {
            data = (size == 0) ? EMPTY_DATA : Arrays.copyOf(data, size);
        }
    }

    public void ensureCapacity(int capacity) {
        if(capacity > size) {
            data = Arrays.copyOf(data, capacity);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[ ");
        for(int i = 0; i < size; ++i) {
            str.append(data[i]).append(" ");
        }
        str.append("]");
        return str.toString();
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

    // To do
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        }
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        if(size == 0) {
            ensureCapacity(DEFAULT_CAPACITY);
        }
        if(size == data.length) {
            ensureCapacity(data.length * 2);
        }
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for(int index = 0; index < size; ++index) {
                if(data[index] == null) {
                    removeHelp(index);
                    return true;
                }
            }
        }
        else {
            for (int index = 0; index < size; index++)
                if (o.equals(data[index])) {
                    removeHelp(index);
                    return true;
                }
        }
        return false;
    }

    private void removeHelp(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index,
                    numMoved);
        }
        data[--size] = null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object e : c) {
            if(!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        ensureCapacity(size + a.length);
        System.arraycopy(a, 0, data, size, a.length);
        size += a.length;
        return a.length != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        Object[] a = c.toArray();
        ensureCapacity(size + a.length);
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(data, index, data, index + a.length,
                    numMoved);

        System.arraycopy(a, 0, data, index, a.length);
        size += a.length;
        return a.length != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for(Object e : c) {
            if(this.contains(e)) {
                this.remove(e);
                size--;
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;
        for(Object e : c) {
            if(!this.contains(e)) {
                this.remove(e);
                size--;
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);

        return (E) data[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);

        Object old = data[index];
        data[index] = element;
        return (E) old;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);

        E old = (E) data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null;
        return old;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size-1; i >= 0; i--) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    //To do
    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public E previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        };
    }

    //To do
    @Override
    public ListIterator<E> listIterator(int index) {
        return listIterator();
    }

    //To do
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new ArrayList<>();
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

}