package task;
import java.util.*;

public class CustomLinkedList<E> implements List<E>,Deque<E>{
    private int size=0;
    private Node<E> first;
    private Node<E> last;

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
        linkFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        return unLinkFirst();
    }

    @Override
    public E removeLast() {
        if (size == 0){
            throw  new NoSuchElementException();
        }
        return unLinkLast();
    }

    @Override
    public E pollFirst() {
        if (size == 0){
            return null;
        }
        return unLinkFirst();
    }

    @Override
    public E pollLast() {
        if (size == 0){
            return null;
        }
        return unLinkLast();
    }

    @Override
    public E getFirst() {
        E e = first.e;
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E getLast() {
        E e = last.e;
        if (size == 0 ){
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E peekFirst() {
        E e = first.e;
        if (size == 0){
            return null;
        }
        return e;
    }

    @Override
    public E peekLast() {
        E e = last.e;
        if (size == 0){
            return null;
        }
        return e;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        for (Node<E> x = last; x != null; x = x.prev) {
            if(o == null) {
                if (x.e == null) {
                    unLink(x);
                    return true;

                }
            }else{
                if (o.equals(x.e))
                    unLink(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return (size == 0 ) ? null : unLinkFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return (size == 0) ? null : getFirst();
    }

    @Override
    public void push(E e) {
        linkFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next){
            objects[i] = x.e;
            i++;
        }
        return objects;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> x = first; x != null; x = x.next) {
            if(o == null) {
                if (x.e == null){
                    unLink(x);
                    return true;
                }
            }else{
                if (o.equals(x.e)) {
                    unLink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c){
            if (!contains(e)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0){
            throw new NullPointerException();
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException();
        Object[] objects = c.toArray();
        for(Object o : objects) {
            remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (Node<E> x = last; x != null; x = x.prev) {
            Node<E> next = x.next;
            x.e = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        isNormalIndex(index);
        return node(index).e;
    }

    @Override
    public E set(int index, E element) {
        isNormalIndex(index);
        Node<E> t = node(index);
        E e = t.e;
        t.e = element;
        return e;
    }

    @Override
    public void add(int index, E element) {
        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    @Override
    public E remove(int index) {
        isNormalIndex(index);
        return unLink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            if(o == null) {
                if (x.e == null)
                    return index;
            }else{
                if (o.equals(x.e))
                    return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        for (Node<E> x = last; x != null; x = x.prev) {
            if(o == null) {
                if (x.e == null)
                    return index;
            }else{
                if (o.equals(x.e))
                    return index;
            }
            index++;
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

    private static class Node<E>{
        E e;
        Node<E> next;
        Node<E> prev;

        public Node(E e, Node<E> prev, Node<E> next) {
            this.e = e;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkLast(E e){
        Node<E> t = last;
        Node<E>  node= new Node<>(e,t,null);
        last = node;
        if(t == null){
            first = node;
        }else {
            t.next = last;
        }
        size++;
    }

    private void linkFirst(E e){
        Node<E> t = first;
        Node<E> node = new Node<>(e,null,t);
        first = node;
        if(t == null){
            last = node;
        }else{
            t.prev = first;
        }
        size++;
    }

    private E unLinkFirst(){
        if (first == null) throw new NoSuchElementException();
        E e = first.e;
        Node<E> t = first.next;
        first.next = null;
        first.e = null;
        if (t == null){
            last = null;
        }else{
            t.prev=null;
        }
        size--;
        return e;

    }

    private E unLinkLast(){
        E e = last.e;
        Node<E> t = last.prev;
        last.e = null;
        last.prev = null;
        last = t;
        if(t == null){
            first = null;
        }else{
            last.next= null;
        }
        size--;
        return e;
    }

    private E unLink(Node<E> element){
        E e = element.e;
        Node<E> next = element.next;
        Node<E> prev = element.prev;
        if(next == null){
            last = prev;
        }else{
            next.prev = prev;
        }
        if (prev == null){
            first = next;
        }else {
            prev.next =next;
        }
        element.e = null;
        element.prev = null;
        element.next = null;
        size--;
        return e;
    }

    private Node<E> node(int index) {
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

    private void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(e, pred, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;

    }

    private void isNormalIndex(int index){
        if( index <=0 || index >= size  )
            throw new IndexOutOfBoundsException();
    }
}
