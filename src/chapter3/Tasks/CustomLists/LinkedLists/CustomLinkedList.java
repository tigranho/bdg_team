package chapter3.Tasks.CustomLists.LinkedLists;

import java.io.Serializable;
import java.util.*;
import java.util.function.IntFunction;

public class CustomLinkedList<E> implements List<E>, Deque<E>, Cloneable, Serializable {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    transient Object[] element;
    transient int head;
    protected transient int modCount = 0;

    public CustomLinkedList(){}

    public CustomLinkedList(Collection<? extends E> c){
        this();
        addAll(c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public Object[] toArray() {

        Object[] result = new Object[size];
        int i = 0;

        for (Node<E> x = first; x != null; x = x.next){
            result[i++] = x.item;
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {

        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);

        int i = 0;

        Object[] result = a;

        for (Node<E> x = first; x != null; x = x.next){
            result[i++] = x.item;
        }

        if (a.length > size)
            a[size] = null;

        return a;
    }

    void linkLast(E e){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;

        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    private void linkFirst(E e) {
        final CustomLinkedList.Node<E> f = first;
        final CustomLinkedList.Node<E> newNode = new CustomLinkedList.Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
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
        final Node<E> f = first;

        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    @Override
    public E removeLast() {
        final Node<E> l = last;

        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    @Override
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    @Override
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    private E unlinkFirst(CustomLinkedList.Node<E> f) {
        final E element = f.item;
        final CustomLinkedList.Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    private E unlinkLast(CustomLinkedList.Node<E> l) {
        final E element = l.item;
        final CustomLinkedList.Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public E getFirst() {
        final Node<E> f = first;
        if(f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    @Override
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
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
        if (o == null){
            for (Node<E> x = last; x != null; x = x.prev){
                if (x.item == null){
                    unlink(x);
                    return true;
                }
            }
        }
        else {
            for(Node<E> x = last; x != null; x = x.prev){
                if (o.equals(x.item)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);

        return true;
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
        final Node<E> f = first;
        return (f == null) ? null : unlink(f);
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public boolean remove(Object o) {

        if (o == null){
            for(Node<E> x = first; x != null; x = x.next){
                if (x.item == null){
                    unlink(x);
                    return true;
                }
            }
        }
        else{
            for (Node<E> x = first; x != null; x = x.next){
                if (o.equals(x.item)){
                    unlink(x);
                    return true;
                }
            }
        }

        return false;
    }

    E unlink(CustomLinkedList.Node<E> x) {
        final E element = x.item;
        final CustomLinkedList.Node<E> next = x.next;
        final CustomLinkedList.Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        Objects.requireNonNull(c);
        boolean modified = false;

        Iterator<?> iterator = iterator();
        while(iterator.hasNext()){
            if (c.contains(iterator.next())){
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

        for(Node<E> x = first; x != null;){
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
        }

        first = last = null;
        size = 0;
        modCount++;
    }

    CustomLinkedList.Node<E> node(int index) {

        if (index < (size >> 1)) {
            CustomLinkedList.Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            CustomLinkedList.Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void checkElementIndex(int index){
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index){
        return "Index: " + index + ", Size: " + size;
    }

    private boolean isElementIndex(int index){
        return index >= 0 && index < size;
    }

    private void checkPositionIndex(int index){
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isPositionIndex(int index){
        return index >= 0 && index < size;
    }

    void linkBefore(E e, CustomLinkedList.Node<E> succ) {
        final CustomLinkedList.Node<E> pred = succ.prev;
        final CustomLinkedList.Node<E> newNode = new CustomLinkedList.Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldValue = x.item;
        x.item = element;

        return oldValue;
    }

    @Override
    public void add(int index, E element) {

        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;

        if (o == null){
            for (Node<E> x = first; x != null; x = x.next){
                if (x.item == null)
                    return index;
                index++;
            }
        }
        else {
            for (Node<E> x = first; x != null; x = x.next){
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null){
            for (Node<E> x = first; x != null; x = x.next){
                index--;
                if (x.item == null){
                    return index;
                }
            }
        }
        else {
            for (Node<E> x = first; x != null; x = x.next){

                index--;

                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
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

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (Node<E> x = first; x != null; x = x.next) {
            sb.append(x.item);

            if (x.equals(size))
                break;
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E>{
        E item;

        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}