package customLists.ArrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Custom implementation of the </ArrayList> interface
 *
 * @author Tatevik Mirzoyan
 */
public class CustomArrayList<T> implements List<T> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Empty array instance used for empty instances.
     */
    private Object[] array;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;


    /**
     * Creates empty list with an initial capacity of ten
     */
    public CustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }


    /**
     * Creates empty list with given initial capacity
     *
     * @param capacity the given initial capacity of the list
     * @throws IllegalArgumentException if the given initial capacity is negative
     */

    public CustomArrayList(int capacity) {
        if (capacity >= 0) {
            this.array = new Object[capacity];
        } else throw new IllegalArgumentException("Illegal capacity" + capacity);
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
        for (int i = 0; i < size; i++) {
            if (array[i] == o)
                return true;
        }
        return false;
    }

    @Override
    public void clear() {
        // The elements are available for GC
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element at the specified position in the list.
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    /**
     * Replaces the element at the specified position in the list with the specified element.
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T replacedElement = (T) array[index];
        array[index] = element;
        return replacedElement;
    }

    /**
     * Adds specified element to the end of the list.
     */
    @Override
    public boolean add(T t) {
        array[size] = t;
        size++;
        return true;
    }


    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list doesn't contain the specified element, it remains unchanged.
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i] == o) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the element at the specified position in the list.
     * And shifts rest elements to the left one position.
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) array[index];
        array[index] = null;
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removedElement;
    }

/////in process
    @Override
    public void add(int i, T t) {
        checkIndex(i);
        Object element;
        for (int j = 0; j < size; j++) {
            if (j == i) {
                element = array[j];
                array[j] = t;
                array[j+1] = element;
                for (int k = j+1; k < size; k++) {
                    array[k + 1] = array[k];
                }
            }//else this.add(t);
        }
        size++;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }


    /**
     * Checks if the index is out of bounds or not.
     *
     * @param index the index that has to be checked
     * @throws IndexOutOfBoundsException .
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
    }



    @Override
    public String toString() {
        System.out.println("This is my list");
        System.out.print("[ ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");

        return "";
    }
}
