package customLists;

/**
 * Resizable-array implementation of the </ArrayList> interface
 *
 * @author Tatevik Mirzoyan
 */
public class MyCustomArrayList<T> {

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
    public MyCustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }


    /**
     * Creates empty list with given initial capacity
     *
     * @param capacity the given initial capacity of the list
     * @throws IllegalArgumentException if the given initial capacity is negative
     */
    public MyCustomArrayList(int capacity) {
        if (capacity >= 0) {
            this.array = new Object[capacity];
        } else throw new IllegalArgumentException("Illegal capacity" + capacity);
    }

    /**
     * Returns the number of elements in the list
     *
     * @return the number of elements in the  list
     */
    public int size() {
        return size;
    }

    /**
     * Returns </true> if the list contains no elements
     *
     * @return </true> if the list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the list
     */
    public void clear() {
        // The elements are available for GC
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Adds specified element to the end of the list.
     *
     * @param t the specified element to be add to the list
     * @return </true>
     */
    public boolean add(T t) {
        array[size] = t;
        size++;
        return true;
    }


    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list doesn't contain the specified element, it remains unchanged.
     *
     * @param t the element to be removed from the list
     * @return </true> if the list contains the specified element
     */
    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t) {
                remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * Removes the element at the specified position in the list.
     * And shifts rest elements to the left one position.
     *
     * @param index the index of the element to be removed from the list
     * @return the removed element
     * @throws IndexOutOfBoundsException .
     */
    @SuppressWarnings("Unchecked Exception")
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


    /**
     * Returns the element at the specified position in the list.
     *
     * @param index the index of the element to be return
     * @return the element at the specified position in the list
     * @throws IndexOutOfBoundsException .
     */
    @SuppressWarnings("Unchecked Exception")
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    /**
     * Replaces the element at the specified position in the list with the specified element.
     *
     * @param index   the index of element to be replaced
     * @param element the element to be stored at the specified position
     * @return the replaced element at the specified position
     * @throws IndexOutOfBoundsException .
     */
    @SuppressWarnings("Unchecked Exception")
    public T set(int index, T element) {
        checkIndex(index);
        T replacedElement = (T) array[index];
        array[index] = element;
        return replacedElement;
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


    /**
     * Returns a string representation of the list.
     *
     * @return a string representation of the list.
     */
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
