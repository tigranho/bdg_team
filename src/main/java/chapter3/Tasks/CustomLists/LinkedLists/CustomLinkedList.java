package chapter3.Tasks.CustomLists.LinkedLists;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
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
    public @NotNull Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public @NotNull Iterator<E> descendingIterator() {
        return new CustomLinkedList.DescendingIterator();
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
        f.next = null;
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
        l.prev = null;
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
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object e : c)
        if (!contains(e))
            return false;
        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        return addAll(size, c);
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
    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        CustomLinkedList.Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            CustomLinkedList.Node<E> newNode = new CustomLinkedList.Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {

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
    public boolean retainAll(@NotNull Collection<?> c) {
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

        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
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

//        checkPositionIndex(index);

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
    public @NotNull ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public @NotNull ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new CustomLinkedList.ListItr(index);
    }

    @Override
    public @NotNull List<E> subList(int fromIndex, int toIndex) {
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

    private class ListItr implements ListIterator<E> {
        private CustomLinkedList.Node<E> lastReturned;
        private CustomLinkedList.Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public E previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

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
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            CustomLinkedList.Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(E e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = e;
        }

        public void add(E e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.item);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class DescendingIterator implements Iterator<E> {
        private final ListItr itr = new ListItr(size());

        public boolean hasNext() {
            return itr.hasPrevious();
        }

        public E next() {
            return itr.previous();
        }

        public void remove() {
            itr.remove();
        }
    }
}