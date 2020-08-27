package lesson3.custom_impl;

import java.io.Serializable;
import java.util.*;

public class CustomLinkedList<E> extends AbstractDataContainer implements List<E>, Deque<E>, Cloneable, Serializable {
    private static final long serialVersionUID = 2807393845689635403L;
    private Node<E> first;
    private Node<E> last;

    public CustomLinkedList() {
    }

    public CustomLinkedList(Collection<E> collection) {
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        if (first == null) {
            first = new Node<>();
            first.item = e;
        } else if (last == null) {
            last = new Node<E>();
            last.prev = first;
            last.item = e;
            first.next = last;
        } else {
            Node<E> newNode = new Node<>();
            newNode.item = last.item;
            newNode.next = last;
            newNode.prev = last.prev;
            last.prev.next = newNode;
            last.prev = newNode;
            last.item = e;
        }
        setAndGet(size() + 1);
        return true;
    }

    @Override
    public void addFirst(E e) {
        if (first == null) {
            first = new Node<>();
        } else {
            Node<E> newNode = new Node<>();
            newNode.item = first.item;
            newNode.prev = first;
            newNode.next = first.next;
            first.next.prev = newNode;
            first.next = newNode;
        }
        setAndGet(size() + 1);
        first.item = e;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException("invalid index");
        if (first == null) {
            add(element);
            return;
        } else if (last == null || index == size()) {
            addLast(element);
            return;
        }
        Node<E> node = index >= size() / 2 ? getFromLast(index, last) : getFromFirst(index, first);
        if (node == null) {
            addFirst(element);
            return;
        }
        Node<E> newNode = new Node<>();
        newNode.prev = node.prev;
        node.prev.next = newNode;
        newNode.next = node;
        node.prev = newNode;
        newNode.item = element;
        setAndGet(size() + 1);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException("invalid index");
        if (first == null || last == null && index != 0) return false;
        Node<E> to = index >= size() / 2 ? getFromLast(index, last) : getFromFirst(index, first);
        if (to == null) to = first;
        Node<E> from = to.prev;
        CustomLinkedList<E> subList = new CustomLinkedList<>();
        for (E el : c) {
            boolean add = subList.add(el);
        }
        Node<E> subListFirst = subList.first;
        Node<E> subListLast = subList.last;
        from.next = subListFirst;
        subListFirst.prev = from;
        subListLast.next = to;
        to.prev = subListLast;
        setAndGet(size() + c.size());
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        CustomLinkedList<E> subList = new CustomLinkedList<>();
        for (E el : collection) {
            boolean add = subList.add(el);
        }
        if (isEmpty()) {
            first = subList.first;
            last = subList.last;
        } else if (last == null) {
            first.next = subList.first;
            subList.first.prev = first;
            last = subList.last;
        } else {
            subList.first.prev = last;
            last.next = subList.first;
            last = subList.last;
        }
        setAndGet(size() + collection.size());
        return true;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public void push(E e) {
        addLast(e);
    }

    @Override
    public E remove() {
        if (last == null) return null;
        E oldValue = last.item;
        last = last.prev;
        last.next = null;
        setAndGet(size() - 1);
        return oldValue;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException("invalid index");
        if (first == null || last == null) return null;
        Node<E> node = index >= size() / 2 ? getFromLast(index, last) : getFromFirst(index, first);
        if (node == null) return null;
        else if (node == first) return removeFirst();
        else if (node == last) return removeLast();
        E oldVal = node.item;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = null;
        setAndGet(size() - 1);
        return oldVal;
    }

    @Override
    public boolean remove(Object o) {
        E el = (E) o;
        Node<E> node = first;
        for (int i = 0; i < size(); i++) {
            if (node.item.equals(el)) break;
            node = node.next;
        }
        if (node == null) return false;
        else if (node == first) return removeFirst() != null;
        else if (node == last) return removeLast() != null;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = null;
        setAndGet(size() - 1);
        return true;
    }

    @Override
    public E removeFirst() {
        if (first == null) return null;
        E oldValue = first.item;
        first = first.next;
        first.prev = null;
        setAndGet(size() - 1);
        return oldValue;
    }

    @Override
    public E removeLast() {
        return remove();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        E el = (E) o;
        Node<E> node = first;
        for (int i = 0; i < size(); i++) {
            if (node.item.equals(el)) break;
            if (i == size() - 1 && !node.item.equals(el))
                return false;
            node = node.next;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = null;
        setAndGet(size() - 1);
        return true;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        E el = (E) o;
        Node<E> node = last;
        for (int i = size() - 1; i >= 0; i--) {
            if (node.item.equals(el)) break;
            if (i == 0 && !node.item.equals(el))
                return false;
            node = node.prev;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = null;
        setAndGet(size() - 1);
        return true;
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        if (last == null) return null;
        return last.item;
    }

    @Override
    public E peek() {
        if (first == null) return null;
        return first.item;
    }

    @Override
    public E pop() {
        return remove();
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException("invalid index");
        if (first == null || last == null) return null;
        Node<E> node = index > size() / 2 ? getFromLast(index, last) : getFromFirst(index, first);
        return node == null ? null : node.item;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index > size()) throw new ArrayIndexOutOfBoundsException("invalid index");
        if (first == null || last == null) return null;
        Node<E> node = index >= size() / 2 ? getFromLast(index, last) : getFromFirst(index, first);
        E oldVal = node != null ? node.item : null;
        if (node != null) node.item = element;
        return oldVal;
    }

    @Override
    public CustomIterator<E> iterator() {
//        Node<E> current = first;
//        List<E> list = new ArrayList<>(size());
//        while (current != null) {
//            list.add(current.item);
//            current = current.next;
//        }
        return new CustomIteratorImpl<E>(this);
    }

    public CustomIterator<E> iterator(int startIndex) {
        return new CustomIteratorImpl<E>(this, startIndex);
    }


    @Override
    public int indexOf(Object o) {
        E el = (E) o;
        Node<E> next = first;
        for (int i = 0; i < size(); i++) {
            if (next.item.equals(el)) return i;
            next = next.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        E el = (E) o;
        Node<E> next = last;
        for (int i = size() - 1; i >= 0; i--) {
            if (next.item.equals(el)) return i;
            next = next.prev;
        }
        return -1;
    }

    @Override
    public void clear() {
        if (first == null) return;
        if (first.next == null) {
            first = null;
            return;
        }
        if (last == null) return;
        first.next = null;
        if (last.prev == null) {
            last = null;
            return;
        }
        last.prev = null;
        first = last = null;
        setAndGet(0);
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex >= toIndex) throw new IllegalArgumentException("from index must be less than to index");
        if (fromIndex < 0 || fromIndex >= size() || toIndex >= size()) throw new ArrayIndexOutOfBoundsException();
        List<E> subList = new CustomArrayList<>(toIndex - fromIndex);
        Node<E> current = first;
        for (int i = 0; i < size(); i++) {
            if (i < fromIndex) continue;
            if (i >= toIndex) break;
            subList.add(current.item);
            current = current.next;
        }
        return subList;
    }

    @Override
    public boolean contains(Object o) {
        E el = (E) o;
        Node<E> next = first;
        for (int i = 0; i < size(); i++) {
            if (next.item.equals(el)) return true;
            next = next.next;
        }
        return false;
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
    public E pollFirst() {
        return removeFirst();
    }

    @Override
    public E pollLast() {
        return removeLast();
    }

    @Override
    public E getFirst() {
        if (first == null) return null;
        return first.item;
    }

    @Override
    public E getLast() {
        if (last == null) return null;
        return last.item;
    }

    @Override
    public E peekFirst() {
        return peek();
    }

    @Override
    public E peekLast() {
        return getLast();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        Node<E> node = first;
        while (node != null) {
            sb.append(node.item);
            if (node.next != null) sb.append(", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        private E item;
        private Node<E> prev;
        private Node<E> next;

    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    private Node<E> getFromLast(int index, Node<E> last) {
        Node<E> next = last;
        for (int i = size() - 1; i > index; i--) {
            if (next == null) return null;
            next = next.prev;
        }
        return next;
    }

    private Node<E> getFromFirst(int index, Node<E> first) {
        Node<E> next = first;
        for (int i = 0; i < index; i++) {
            if (next == null) return null;
            next = next.next;
        }
        return next;
    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(5);
        customLinkedList.add(2);
        customLinkedList.add(4);
        customLinkedList.add(1);
        customLinkedList.addFirst(8);
        System.out.println(customLinkedList + " size = " + customLinkedList.size());
        System.out.println(customLinkedList.remove());
        System.out.println(customLinkedList + " size = " + customLinkedList.size());
        System.out.println(customLinkedList.get(1));
        System.out.println(customLinkedList.get(3));
        customLinkedList.addFirst(6);
        System.out.println(customLinkedList + " size = " + customLinkedList.size());
        customLinkedList.set(2, 3);
        System.out.println(customLinkedList + " size = " + customLinkedList.size());
        customLinkedList.add(1, 7);
        System.out.println(customLinkedList + " size = " + customLinkedList.size());
        customLinkedList.remove(3);
        System.out.println(customLinkedList + " size = " + customLinkedList.size());

        customLinkedList.addAll(1, Arrays.asList(11, 12, 13, 14));
        System.out.println(customLinkedList + " size = " + customLinkedList.size());
    }

    @Override
    public ListIterator<E> listIterator() {
//        Node<E> current = first;
//        List<E> list = new ArrayList<>(size());
//        while (current != null) {
//            list.add(current.item);
//            current = current.next;
//        }
//        return list.listIterator();
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
//        Node<E> current = first;
//        List<E> list = new ArrayList<>(size());
//        while (current != null) {
//            list.add(current.item);
//            current = current.next;
//        }
//        return list.listIterator(i);
        return null;
    }
}
