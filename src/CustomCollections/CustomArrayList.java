package CustomCollections;

import java.util.Arrays;
import java.util.Collection;

/**
 * @param <T>
 * @author VaheAvetikyan
 * sggested changes by Astghik comments with " // "
 */
public class CustomArrayList<T> {

// Create custom generic List interface and implement it for your customArrayList class
    /**
     * Shared empty array instance used for empty instances.
     */
    private final Object[] EMPTY_ARRAY = {};

    /**
     * The array buffer
     */
    private Object[] dataArray;

    /**
     * The size of the ArrayList.
     */
    private int size;

// Add field for default capacity and use it in no arguments constructor for initializing array field

    /**
     * Constructs an empty list.
     */

// No arguments constructor should initialize non empty array with non zero capacity
    public CustomArrayList() {
        this.dataArray = EMPTY_ARRAY;
        this.size = 0;
    }

    /**
     * Constructs a list with the elements of specified collection.
     */
// Pass as a parameter your custom interface type object not Java Collection interface type object
    public CustomArrayList(Collection<? extends T> initialArray) {
        this.dataArray = initialArray.toArray();
        this.size = dataArray.length;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if list is empty, false otherwise
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if a certain value is in the list
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * @param numberOfElements is added to the size of list if it exceeds current array length,
     *                         else the size is doubled.
     * @return new Object array
     */

// Write your custom methods and not use methods of Java Collection interface and Arrays's classes methods
    private Object[] grow(int numberOfElements) {
        if (this.size + numberOfElements > this.dataArray.length) {
            return this.dataArray = Arrays.copyOf(this.dataArray, Math.max(this.size * 2, this.size + numberOfElements));
        }
        return this.dataArray;
    }

    // ArrayList size by default grow by its behalf
    private Object[] grow() {
        return grow(1);
    }


    private void add(T t, Object[] data, int s) {
        if (s == this.dataArray.length) {
            this.dataArray = grow();
        }
        this.dataArray[s] = t;
        size++;
    }

    /**
     * Adds element to end
     */
    public void add(T t) {
        add(t, dataArray, size);
    }

    /**
     * Adds element at index and moves the rest toward the end
     */
// method works wrong
// CORRECTED
    public void add(int index, T t) {
        this.dataArray = grow(1);
        this.size++;
        if (index >= 0 && index < this.size) {
            T temp = set(index, t);
            for (int i = index + 1; i < this.size; i++) {
                temp = set(i, temp);
            }
        }
    }

    /**
     * Removes element at index and moves the rest toward the front
     */
// There is no check for ArrayIndexOutOfItsBound exception in remove method by index
// CORRECTED
    public T remove(int index) {
        T temp = null;
        if (index >= 0 && index < this.size) {
            temp = (T) this.dataArray[index];
            for (int i = index; i < this.size - 1; i++) {
                set(i, (T) this.dataArray[i + 1]);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.dataArray[size - 1] = null;
        size--;
        return temp;
    }

    public void remove(T t) {
        remove(indexOf(t));
    }

    /**
     * Discards all elements of the list
     */
    //    public void clear() {
    //        Object[] tempArray = this.dataArray;
    //        while (size > 0) {
    //            size--;
    //            tempArray[size] = null;
    //        }
    //    }
    public void clear() {
        this.dataArray = EMPTY_ARRAY;
        this.size = this.dataArray.length;
    }

    /**
     * Returns element at @param index
     *
     * @return element at @param index
     */
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) dataArray[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Replaces element at index and returns original
     */
    public T set(int index, T t) {
        Object temp = null;
        if (index >= 0 && index < this.size) {
            temp = dataArray[index];
            this.dataArray[index] = t;
        }
        return (T) temp;
    }

    public int indexOf(Object o) {
        return indexOfRange(o, 0, this.size);
    }

    /**
     * Returns last matching index or -1 if not found
     */
// method work wrong
// CORRECTED
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, this.size);
    }

    private int indexOfRange(Object o, int start, int end) {
        Object[] newRef = this.dataArray;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (newRef[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(newRef[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int lastIndexOfRange(Object o, int start, int end) {
        Object[] newRef = this.dataArray;
        if (o == null) {
            for (int i = end - 1; i > start; i--) {
                if (newRef[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i > start; i--) {
                if (o.equals(newRef[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(dataArray[i]).append(" ");
        }
        return s.toString();
    }
}