package listTask;


import java.util.*;

public class CustomLinkedList<E> implements List<E>,Deque<E>{
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;


    public CustomLinkedList() {
    }


    public CustomLinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public void addFirst(E e) {
        Node<E> f = first;
        Node<E> n = new Node<>(null,e,f);
        first = n;
        if (f == null){
            last = n;
        }else{
            f.prev = n;
        }
    }

    @Override
    public void addLast(E e) {
        Node<E> l = last;
        Node<E> n = new Node<>(l,e,null);
        last = n;
        if (l == null){
            l = n;
        }else{
            l.next = n;
        }
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
        if (first != null){
            return unLinkFirst(first);
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public E removeLast() {
        if (last != null){
            return unlinkLast(last);
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public E pollFirst() {
        final Node<E> f = first;
        return (f!=null)?unLinkFirst(f):null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        final Node<E> l = last;
        return (l!=null)?unlinkLast(l):null;
    }

    @Override
    public E getLast() {
        return last.element;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
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
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
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

    private E unLinkFirst(Node<E> node){
        E element = node.element;
        Node<E> next = node.next;
        node.element = null;
        node.next = null;
        first = next;
        if (next == null){
            last = null;
        }else{
            first.prev  = null;
        }
        size--;
        return element;
    }
    private E unlinkLast(Node<E> node) {

        E element = node.element;
        Node<E> prev = node.prev;
        node.element = null;
        node.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element,Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

    }
}
