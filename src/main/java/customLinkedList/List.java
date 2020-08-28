package customLinkedList;

public interface List<E> {
    int getSize();

    boolean isEmpty();

    boolean add(E element);

    void add(int index, E element);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    boolean addAll(List<? extends E> list);

    boolean addAll(int position, List<? extends E> list);

    boolean remove(Object object);
}
