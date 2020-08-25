package CustomCollections.CustomList;

/**
 * @param <E>
 * @author VaheAvetikyan
 */
public class CustomLinkedList<E> implements CustomList<E> {

    /**
     * Helper Node class
     *
     * @param <E>
     */
    private class Node<E> {
        Node<E> prev;
        E element;
        Node<E> next;

        Node(Node<E> prev, E elem, Node<E> next) {
            this.prev = prev;
            this.element = elem;
            this.next = next;
        }
    }

    /**
     * Pointer to first node.
     */
    private Node<E> first;

    /**
     * Pointer to last node.
     */
    private Node<E> last;

    private int size;

    /**
     * Constructs an empty list.
     */
    public CustomLinkedList() {
    }

    /**
     * Constructs a list with one element.
     */
    public CustomLinkedList(E e) {
        addTheFirstElement(e);
    }

    /**
     * Returns the number of elements in the list
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if list is empty, false otherwise
     *
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

    public void addFirst(E e) {
        if (this.size > 0) {
            Node<E> oldFirst = this.first;
            Node<E> newNode = new Node<E>(null, e, oldFirst);
            oldFirst.prev = newNode;
            this.first = newNode;
            this.size++;
        } else {
            addTheFirstElement(e);
        }
    }

    public void addLast(E e) {
        if (this.size > 0) {
            Node<E> oldLast = this.last;
            Node<E> newNode = new Node<E>(oldLast, e, null);
            oldLast.next = newNode;
            this.last = newNode;
            this.size++;
        } else {
            addTheFirstElement(e);
        }
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (index == 0) {
            addFirst(element);
            return true;
        }
        if (index == size) {
            addLast(element);
            return true;
        }
        Node<E> oldElementInIndex = getElementByIndex(index);
        Node<E> previous = oldElementInIndex.prev;
        Node<E> newNode = new Node<E>(previous, element, oldElementInIndex);
        previous.next = newNode;
        oldElementInIndex.prev = newNode;
        size++;
        return true;
    }

    private void addTheFirstElement(E e) {
        this.first = new Node<E>(null, e, null);
        this.last = this.first;
        this.size++;
    }

    /**
     * Removes element at index
     */
    @Override
    public E remove(int index) {
        Node<E> node = getElementByIndex(index);
        unlink(node);
        return node.element;
    }

    @Override
    public void remove(E e) {
        if (e == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.element == null) {
                    unlink(node);
                }
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (e.equals(node.element)) {
                    unlink(node);
                }
            }
        }
    }

    public E removeFirst() {
        return unlinkFirst();
    }

    public E removeLast() {
        return unlinkLast();
    }

    private E unlinkFirst() {
        Node<E> temp = this.first;
        this.first = this.first.next;
        this.size--;
        return temp.element;
    }

    private E unlinkLast() {
        Node<E> temp = this.last;
        this.last = this.last.prev;
        this.size--;
        return temp.element;
    }

    private E unlink(Node<E> node) {
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
        }

        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
        }
        this.size--;
        return node.element;
    }

    /**
     * Discards all elements of the list
     */
    @Override
    public void clear() {
        Node<E> next = this.first;
        while (next != null) {
            Node<E> temp = next.next;
            next.prev = null;
            next.element = null;
            next.next = null;
            next = temp;
        }
        this.first = this.last = null;
        this.size = 0;
    }

    /**
     * Returns element at @param index
     *
     * @return element at @param index
     */
    @Override
    public E get(int index) {
        Node<E> node = getElementByIndex(index);
        return node.element;
    }

    /**
     * Replaces element at index and returns original
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = getElementByIndex(index);
        E temp = node.element;
        node.element = element;
        return temp;
    }

    private Node<E> getElementByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<E> node = this.first;
        int i = 0;
        while (node.next != null) {
            if (i == index) {
                break;
            }
            node = node.next;
            i++;
        }
        return node;
    }

    /**
     * Returns first matching index or -1 if not found
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> node = first; node != null; node = node.next) {
                if (node.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> node = first; node != null; node = node.next) {
                if (o.equals(node.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns last matching index or -1 if not found
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> node = last; node != null; node = node.prev) {
                index--;
                if (node.element == null)
                    return index;
            }
        } else {
            for (Node<E> node = last; node != null; node = node.prev) {
                index--;
                if (o.equals(node.element))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<E> node = first;
        while (node != null) {
            s.append(node.element).append(" ");
            node = node.next;
        }
        return s.toString();
    }
}
