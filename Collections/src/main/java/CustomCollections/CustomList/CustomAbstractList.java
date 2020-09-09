package CustomCollections.CustomList;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public abstract class CustomAbstractList<E> implements List<E> {
    /**
     * The size of the List.
     */
    protected int size;

    /**
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks if a certain value is in the list
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean added = false;
        for (E e : c)
            if (add(e)) {
                added = true;
            }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
