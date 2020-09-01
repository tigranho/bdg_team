package customLists.LinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Tatevik Mirzoyan
 */
public class MyCustomLinkedList<T> implements List<T> {
    private int size = 0;
    private Node<T> first;   // todo
    private Node<T> last;
    private int modCount = 0;

    /**
     * Default constructor of an empty list.
     */
    public MyCustomLinkedList() {
    }

    //???
    @Override
    public boolean remove(Object o) {
        if (last == null) {
            System.out.println("Empty list");
        } else {
            Node<T> currentNode = last;
            while (currentNode.next.next != null) {
                if (currentNode.next.item == o) {
                    currentNode.next = currentNode.next.next;
                }
                currentNode = currentNode.next;
            }
        }
        size--;
        return false;
    }

    //???

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> removedNode;
        Node<T> currentNode = last;
        while (currentNode.next != null) {
            removedNode = node(index);
            remove(removedNode.item);
            currentNode = currentNode.next;
        }
        return null;
    }
    // OK ???

    @Override
    public boolean add(T t) {
        Node<T> newNode = new Node<>(t);
        if (last == null) {
            last = newNode;
        } else {
            Node<T> currentNode = last;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
        modCount++;
        return true;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    @Override
    public T set(int index, T t) {
        checkIndex(index);
        Node<T> oldNode = last;
        Node<T> newNode = new Node<>(t);
        Node<T> currentNode = last;
        while (currentNode.next != null) {
            oldNode = node(index);
            node(index).item = newNode.item;
            currentNode = currentNode.next;
        }
        return oldNode.item;
    }

    @Override
    public void add(int i, Object o) {

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

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
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

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
    public String toString() {
        if (last == null) {
            System.out.println("Empty list");
        } else {
            System.out.println("This is my list");
            Node<T> currentNode = last;
            while (currentNode.next != null) {
                System.out.print(currentNode.item + " ");
                currentNode = currentNode.next;
            }
            System.out.print(currentNode.item);
        }
        return "";
    }

    private Node<T> node(int index) {
        Node<T> indexNode = last;
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
