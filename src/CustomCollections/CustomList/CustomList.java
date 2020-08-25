package CustomCollections.CustomList;

public interface CustomList<E> {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    boolean add(E e);
    boolean add(int index, E e);
    E remove(int index);
    void remove(E e);
    void clear();
    E get(int index);
    E set(int index, E e);
    int indexOf(Object o);
    int lastIndexOf(Object o);
}
