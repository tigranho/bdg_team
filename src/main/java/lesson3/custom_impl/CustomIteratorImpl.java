package lesson3.custom_impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * @author Hrach
 */

class CustomIteratorImpl<T> implements Iterator<T> {

    private int modeCount;
    private int cursor;
    private final List<T> originalElements;

    /**
     * @throws NullPointerException when parameter is null
     * @param elements is the main source for traversing elements
     */
    public CustomIteratorImpl(List<T> elements) {
        if (elements == null) throw new NullPointerException("list cannot be null");
        this.originalElements = elements;
        modeCount = elements.size();
    }

    /**
     * @throws NullPointerException when parameter "elements" is null
     * @throws IllegalArgumentException when param "startIndex" is less then 0 and equal or grater of param "elements" size
     * @param elements is the main source for traversing elements
     * @param startIndex is the starting position of the source to traverse the elements
     */
    public CustomIteratorImpl(List<T> elements, int startIndex) {
        if (elements == null) throw new NullPointerException("list cannot be null");
        if (startIndex < 0 || startIndex > elements.size()) throw new IllegalArgumentException("invalid index " + startIndex);
        this.originalElements = elements;
        modeCount = elements.size();
        cursor = startIndex;
    }

    /**
     * @return false if the cursor has reached the end of the source
     */
    @Override
    public boolean hasNext() {
        return cursor != originalElements.size();
    }

    /**
     * @return the next element of structure
     * @throws ConcurrentModificationException if originalElements was changed parallel
     * @throws NoSuchElementException if the cursor has reached the end of the source,
     * to avoid getting an exception, you must call the method hasNext() before calling the method next()
     */
    @Override
    public T next() {
        if (modeCount != originalElements.size()) throw new ConcurrentModificationException("Parallel main source modifications");
        if (cursor == originalElements.size()) throw new NoSuchElementException("There are no elements to traverse on them");
        return originalElements.get(cursor++);
    }

    /**
     * Removes the element the cursor is on from the "originalElements"
     * @throws ConcurrentModificationException if originalElements was changed parallel
     */
    @Override
    public void remove() {
        if (modeCount != originalElements.size()) throw new ConcurrentModificationException("Parallel main source modifications");
        originalElements.remove(--cursor);
        modeCount = originalElements.size();
    }

    /**
     * traverses elements starting at the cursor position
     * @param action is a function to be taken for each element during the traversal
     * @throws ConcurrentModificationException if originalElements was changed parallel
     */
    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        if (modeCount != originalElements.size()) throw new ConcurrentModificationException("Parallel main source modifications");
        for (int i = cursor; i < originalElements.size(); i++) {
            action.accept(originalElements.get(i));
        }
    }
}
