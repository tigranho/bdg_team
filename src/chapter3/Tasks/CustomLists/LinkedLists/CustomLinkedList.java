package chapter3.Tasks.CustomLists.LinkedLists;

import java.io.Serializable;
import java.util.*;
import java.util.function.*;

public class CustomLinkedList<T> extends LinkedList<T> implements List<T>, Deque<T>, Cloneable, Serializable {

    transient int size = 0;

    @Override
    public void replaceAll(UnaryOperator<T> operator) {

    }

    @Override
    public void sort(Comparator<? super T> c) {

    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return null;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return false;
    }

    public CustomLinkedList() {
        super();
    }

    public CustomLinkedList(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public T getFirst() {
        return super.getFirst();
    }

    @Override
    public T getLast() {
        return super.getLast();
    }

    @Override
    public T removeFirst() {
        return super.removeFirst();
    }

    @Override
    public T removeLast() {
        return super.removeLast();
    }

    @Override
    public void addFirst(T t) {
        super.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        super.addLast(t);
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean add(T t) {
        return super.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return super.addAll(index, c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public T get(int index) {
        return super.get(index);
    }

    @Override
    public T set(int index, T element) {
        return super.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
    }

    @Override
    public T remove(int index) {
        return super.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public T peek() {
        return super.peek();
    }

    @Override
    public T element() {
        return super.element();
    }

    @Override
    public T poll() {
        return super.poll();
    }

    @Override
    public T remove() {
        return super.remove();
    }

    @Override
    public boolean offer(T t) {
        return super.offer(t);
    }

    @Override
    public boolean offerFirst(T t) {
        return super.offerFirst(t);
    }

    @Override
    public boolean offerLast(T t) {
        return super.offerLast(t);
    }

    @Override
    public T peekFirst() {
        return super.peekFirst();
    }

    @Override
    public T peekLast() {
        return super.peekLast();
    }

    @Override
    public T pollFirst() {
        return super.pollFirst();
    }

    @Override
    public T pollLast() {
        return super.pollLast();
    }

    @Override
    public void push(T t) {
        super.push(t);
    }

    @Override
    public T pop() {
        return super.pop();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return super.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return super.removeLastOccurrence(o);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public Iterator<T> descendingIterator() {
        return super.descendingIterator();
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return super.toArray(a);
    }

    @Override
    public Spliterator<T> spliterator() {
        return super.spliterator();
    }

    @Override
    public Iterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return super.listIterator();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}