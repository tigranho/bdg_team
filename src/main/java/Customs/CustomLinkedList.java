package Customs;

import java.util.*;

public class CustomLinkedList<E> implements List<E>, Deque<E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    public CustomLinkedList() {}

    public CustomLinkedList(Collection<? extends E> c) {
        addAll(c);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[ ");
        for(Node<E> x = first; x != null; x = x.next) {
            str.append(x.item).append(" ");
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public void addFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        }
        else {
            f.prev = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
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

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if(first == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        final Node<E> f = first;
        return unlink(f);
    }

    @Override
    public E removeLast() {
        if(last == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        final Node<E> l = last;
        return unlink(l);
    }

    @Override
    public E pollFirst() {
        return (first == null) ? null : removeFirst();
    }

    @Override
    public E pollLast() {
        return (last == null) ? null : removeLast();
    }

    @Override
    public E getFirst() {
        if(first == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        final Node<E> f = first;
        return f.item;
    }

    @Override
    public E getLast() {
        if(last == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        final Node<E> l = last;
        return l.item;
    }

    @Override
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return (first == null) ? null : removeFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    //To do
    @Override
    public Iterator<E> descendingIterator() {
        return iterator();
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
        return indexOf(o) != -1;
    }

    //To do
    @Override
    public Iterator<E> iterator() {
       return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for(Node<E> x = first; x != null; x = x.next) {
            arr[i++] = x.item;
        }
        return arr;
    }

    //To do
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) new Object[5];
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(o == null) {
            for(Node<E> x = first; x != null; x = x.next) {
                if(x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        }
        else {
            for(Node<E> x = first; x != null; x = x.next) {
                if(o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object e : c) {
            if(!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.size() == 0) {
            return false;
        }
        for(E e : c) {
            addLast(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(c.size() == 0) {
            return false;
        }
        for(E e : c) {
            add(index++, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for(Object e : c) {
            if(contains(e)) {
                remove(e);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;
        for(Object e : c) {
            if(!contains(e)) {
                remove(e);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        for(Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if(first == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        checkIndex(index);

        Node<E> current = first;
        while(index-- != 0) {
            current = current.next;
        }
        final Node<E> result = current;
        return result.item;
    }

    @Override
    public E set(int index, E element) {
        if(first == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        checkIndex(index);

        Node<E> current = first;
        while(index-- != 0) {
            current = current.next;
        }
        final E result = current.item;
        current.item = element;
        return result;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);

        Node<E> current = first;
        while(index-- != 0) {
            current = current.next;
        }

        if(current.prev == null) {
            addFirst(element);
        }
        else if(current.next == null) {
            addLast(element);
        }
        else {
            Node<E> newNode = new Node<>(current.prev, element, current);
            current.prev = newNode;
            current.prev.next = newNode;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        Node<E> current = first;
        while(index-- != 0) {
            current = current.next;
        }
        final E element = current.item;
        unlink(current);
        return element;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if(o == null) {
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
        if(o == null) {
            for(Node<E> x = last; x != null; x = x.prev) {
                if(x.item == null) {
                    return index;
                }
                index--;
            }
        }
        else {
            for(Node<E> x = last; x != null; x = x.prev) {
                if(o.equals(x.item)) {
                    return index;
                }
                index--;
            }
        }
        return -1;
    }

    //To do
    @Override
    public ListIterator<E> listIterator() {
        return (ListIterator<E>) iterator();
    }

    //To do
    @Override
    public ListIterator<E> listIterator(int index) {
        return (ListIterator<E>) iterator();
    }

    //To do
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new ArrayList<>();
    }
    private void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
    }

    private E unlink(Node<E> toUnlink) {
        final E element = toUnlink.item;
        final Node<E> prev = toUnlink.prev;
        final Node<E> next = toUnlink.next;

        if(prev == null) {
            first = next;
        }
        else {
            prev.next = next;
            toUnlink.prev = null;
        }
        if(next == null) {
            last = prev;
        }
        else {
            next.prev = prev;
            toUnlink.next = null;
        }
        toUnlink.item = null;
        size--;
        return element;
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

}
