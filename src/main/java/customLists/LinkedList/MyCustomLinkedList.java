package customLists.LinkedList;

import java.util.*;

/**
 * @author Tatevik Mirzoyan
 */
public class MyCustomLinkedList<T> implements List<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;
    private int modCount = 0;

    /**
     * Default constructor of an empty list.
     */
    public MyCustomLinkedList() {
    }

    @Override //OK
    public boolean add(T t) {
        Node<T> lastNode = last;
        Node<T> newNode = new Node<>(t, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override  //OK
    public void add(int i, Object o) {
        if (i == size) {
            add((T) o);
        } else if (i == 0) {
            Node<T> newNode = new Node<>((T) o);
            newNode.next = first;
            first = newNode;
        } else {
            checkIndex(i);
            Node<T> oldNode = node(i);
            Node<T> prevNode = node(i - 1);
            Node<T> newNode = new Node<>((T) o);
            prevNode.next = newNode;
            newNode.next = oldNode;

        }
        size++;
        modCount++;
    }

    @Override //OK
    public boolean remove(Object o) {
        Node<T> currentNode = first;
        for (Node<T> x = first; x.next != null; x = x.next) {
            if (x.next.item == o) {
                x.next = x.next.next;
                size--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override  //OK
    public T remove(int index) {
        checkIndex(index);
        Node<T> removedNode = node(index);
        T removedItem = removedNode.item;
        if (index == 0) {
            first = removedNode.next;
            size--;
            modCount++;
        } else {
            remove(removedItem);
        }
        return removedItem;
    }

    @Override //OK
    public T get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    @Override  //OK
    public T set(int index, T t) {
        checkIndex(index);
        T oldItem = node(index).item;
        node(index).item = t;
        return oldItem;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    /**
     * Checks if the index is out of bounds or not.
     *
     * @param index the index that has to be checked
     * @throws IndexOutOfBoundsException .
     */
    //OK
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override //OK
    public int size() {
        return size;
    }

    @Override  //OK
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override  //OK
    public void clear() {
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
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

    @Override  //OK
    public int indexOf(Object o) {
        int index = 0;
        for (Node<T> x = first; x != null; x = x.next) {
            if (o.equals(x.item))
                return index;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
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

    @Override  //OK
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("[]");
        } else {
            Node<T> currentNode = first;
            sb.append("[");
            while (currentNode.next != null) {
                sb.append(currentNode.item.toString()).append(", ");
                currentNode = currentNode.next;
            }
            sb.append(last.item.toString()).append("]");
        }
        return sb.toString();
    }

    //OK
    private Node<T> node(int index) {
        Node<T> indexNode = first;
        for (int i = 0; i < index; i++)
            indexNode = indexNode.next;
        return indexNode;
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }

        public Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;

        }
    }

}
