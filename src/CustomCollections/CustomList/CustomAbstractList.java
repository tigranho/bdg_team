package CustomCollections.CustomList;

public abstract class CustomAbstractList<E> implements CustomList<E>{
    /**
     * The size of the List.
     */
    private int size;

    /**
     * Returns the number of elements in the list
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if list is empty, false otherwise
     *
     * @return true if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if a certain value is in the list
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
}
