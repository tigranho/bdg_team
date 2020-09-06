package sam.util;

import java.util.*;
import java.util.function.Consumer;

public class CustomLinkedList<E> extends AbstractList<E> implements List<E> {
    int size = 0;

    Node<E> first;

    Node<E> last;

    public CustomLinkedList() {
    }



    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);

        first = newNode;

        if(f == null) {
            last = newNode;
        }
        else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkBefore(E e, Node<E> succ) {
        Node<E> pred = succ.prev;
        Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        }
        else {
            pred.next = newNode;
        }
        size++;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if(l == null) {
            first = newNode;
        }
        else {
            l.next = newNode;
        }
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;

        f = null;

        first = next;

        if(next == null) {
            last = null;
        }
        else {
            next.prev = null;
        }
        size--;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        E element = l.item;
        Node<E> prev = l.prev;

        l = null;
        last = prev;
        if(prev == null) {
            first = null;
        }
        else {
            prev.next = null;
        }

        size--;
        return element;
    }

    private E unlink(Node<E> x) {


        if(x.prev == null)
        {
            first = x.next;
        } else {
            x.prev.next = x.next;
            x.prev = null;
        }

        if (x.next == null) {
            last = x.prev;
        } else {
            x.next.prev = x.prev;
            x.next = null;
        }

        E element = x.item;
        x.item = null;
        size--;
        return element;
    }

    private void checkIndex(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> node(int index) {
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public E getFirst() {
        final Node<E> f = first;
        if(f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }

    public void addLast(E e) {
        linkLast(e);
    }

    public E removeFirst() {
        if(first == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(first);
    }

    public E removeLast() {
        if(last == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(last);
    }

    @Override
    public void clear() {
        for (CustomLinkedList.Node<E> x = first; x != null; ) {
            CustomLinkedList.Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E newValue) {
        checkIndex(index);
        Node<E> x = node(index);
        E oldValue = x.item;
        x.item = newValue;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        if (index == size) {
            linkLast(element);
        }
        else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if(o == null)  {
            for(Node<E> x = first; x != null; x = x.next) {
                if(x.item == null) {
                    return index;
                }
                index++;
            }
        }
        else {
            for(Node<E> x = first; x != null; x = x.next) {
                if(o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    //Queue operations

    public E peek() {
        return (first == null)? null : first.item;
    }

    public E element() {
        return getFirst();
    }

    public E poll() {
        return (first == null) ? null : unlinkFirst(first);
    }

    public E remove() {
        return removeFirst();
    }

    public boolean offer(E e) {
        return add(e);
    }

    //Deque operations;

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    public E peekFirst() {
        return (first == null)? null: first.item;
    }

    public E peekLast() {
        return (last == null) ? null : last.item;
    }

    public E pollFirst() {
        return (first == null) ? null : unlinkFirst(first);
    }

    public E pollLast() {
        return (last == null) ? null : unlinkLast(last);
    }

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        return removeFirst();
    }


    public ListIterator<E> listIterator(int index) {
        checkIndex(index);
        return new ListItr(index);
    }

    public Iterator<E> iterator() {
        checkIndex(0);
        return new ListItr(0);
    }

    private class ListItr implements ListIterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            if (!hasNext())
                throw new ConcurrentModificationException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public E previous() {
            if (!hasPrevious())
                throw new ConcurrentModificationException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
        }

        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.item = e;
        }

        public void add(E e) {
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
        }

    }
}
