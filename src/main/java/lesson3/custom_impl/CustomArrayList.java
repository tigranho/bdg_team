package lesson3.custom_impl;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


/**
 * @param <T> access all reference types
 * @author Hrach
 */
public class CustomArrayList<T> extends AbstractDataContainer implements List<T>, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUUID = 7363745724264622942L;
    private static final byte DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int capacity;


    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }


    @SuppressWarnings("unchecked")
    public CustomArrayList(int length) {
        if (length < 0) throw new IllegalArgumentException("length must be positive number");
        elements = (T[]) new Object[length];
        capacity = length;
    }

    @SuppressWarnings("unchecked")
    public CustomArrayList(Collection<? extends T> collection) {
        if (collection == null) throw new NullPointerException("argument cannot be null");
        elements = (T[]) collection.toArray();
        capacity = setAndGet(elements.length);
    }

    @Override
    public boolean add(T t) {
        if (size() == capacity) grow(1);
        elements[getAndSet(size() + 1)] = t;
        return true;
    }

    /**
     * Clears the list leaving the same capacity
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (T[]) new Object[capacity];
        setAndGet(0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            sb.append(elements[i]);
            if (i == size() - 1) break;
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        if (action == null) throw new NullPointerException("argument cannot be null");
        for (int i = 0; i < size(); i++) {
            action.accept(elements[i]);
        }
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        if (filter == null) throw new NullPointerException();
        for (int i = 0; i < size(); i++) {
            if (filter.test(elements[i])) {
                for (int j = i; j < size() - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[setAndGet(size() - 1)] = null;
                return true;
            }

        }
        return false;
    }

    @Override
    public Stream<T> stream() {
        return Arrays.stream(elements);
    }

    @Override
    public boolean addAll(int idx, Collection<? extends T> collection) {
        if (collection == null) throw new NullPointerException("argument cannot be null");
        if (idx < 0 || idx >= capacity) throw new IllegalArgumentException("invalid index");
        if (capacity - size() <= collection.size()) grow(collection.size());
        @SuppressWarnings("unchecked")
        T[] newElements = (T[]) collection.toArray();
        for (int i = idx, j = idx + newElements.length, z = 0; z <= size() - idx; i++, j++, z++) {
            elements[j] = elements[i];
        }
        setAndGet(size() + newElements.length);
        for (int i = idx, j = 0; j < newElements.length; i++, j++) {
            elements[i] = newElements[j];
        }
        return true;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        if (operator == null) throw new NullPointerException("argument cannot be null");
        for (int i = 0; i < size(); i++) {
            elements[i] = operator.apply(elements[i]);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        if (c == null) throw new NullPointerException("argument cannot be null");
        @SuppressWarnings("unchecked")
        T[] sorted = (T[]) new Object[size()];
        System.arraycopy(elements, 0, sorted, 0, size());
        Arrays.sort(sorted, c);
        for (int i = 0; i < size(); i++) {
            elements[i] = sorted[i];
        }
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= capacity) throw new ArrayIndexOutOfBoundsException("invalid index");
        return elements[i];
    }

    @Override
    public T set(int i, T t) {
        if (i < 0 || i >= capacity) throw new ArrayIndexOutOfBoundsException("invalid index");
        T oldVal = elements[i];
        elements[i] = t;
        return oldVal;
    }

    @Override
    public void add(int idx, T t) {
        if (idx < 0 || idx >= capacity) throw new ArrayIndexOutOfBoundsException("invalid index");
        if (size() == capacity) grow(1);
        for (int i = getAndSet(size() + 1); i > idx; i--) {
            elements[i] = elements[i - 1];
        }
        elements[idx] = t;
    }

    @Override
    public boolean contains(Object o) {
        T t = (T) o;
        for (int i = 0; i < size(); i++) {
            if (o == null && elements[i] == null ||
                    o != null && o.equals(elements[i]))
                return true;
        }
        return false;
    }


    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("argument cannot be null");
        @SuppressWarnings("unchecked")
        T[] elems = (T[]) c.toArray();
        int count = elems.length;
        for (T t : elems) {
            if (indexOf(t) != -1) count--;
        }
        return count == 0;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) throw new NullPointerException("argument cannot be null");
        if (capacity - size() <= collection.size()) grow(collection.size());
        @SuppressWarnings("unchecked")
        T[] newElements = (T[]) collection.toArray();
        setAndGet(size() + newElements.length);
        for (int i = size(), j = 0; j < newElements.length; i++, j++) {
            elements[i] = newElements[j];
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("argument cannot be null");
        boolean flag = false;
        for (T t : (T[]) c.toArray()) {
            if (remove(t)) flag = true;
        }
        return flag;
    }


    @Override
    public T remove(int idx) {
        if (idx < 0 || idx >= capacity) throw new ArrayIndexOutOfBoundsException("invalid index");
        T oldVal = elements[idx];
        for (int j = idx; j < size() - 1; ) {
            elements[j] = elements[++j];
        }
        setAndGet(size() - 1);
        return oldVal;
    }

    @Override
    public boolean remove(Object o) {
        T t = (T) o;
        for (int i = 0; i < size(); i++) {
            if (o == null && elements[i] == null ||
                    o != null && o.equals(elements[i])) {
                for (int j = i; j < size() - 1; ) {
                    elements[j] = elements[++j];
                }
                setAndGet(size() - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        T t = (T) o;
        for (int i = 0; i < size(); i++) {
            if (o == null && elements[i] == null ||
                    o != null && o.equals(elements[i]))
                return i;
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object o) {
        T t = (T) o;
        for (int i = size() - 1; i >= 0; i--) {
            if (o == null && elements[i] == null ||
                    o != null && o.equals(elements[i]))
                return i;
        }
        return -1;
    }

    @Override
    public List<T> subList(int start, int end) {
        if (start < 0 || start >= capacity || end < 0 || end >= capacity)
            throw new ArrayIndexOutOfBoundsException("invalid index");
        if (start >= end) throw new IllegalArgumentException("the start index must be less than end index");
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[end - start];
        System.arraycopy(elements, start, newArray, 0, end - start);
        List<T> result = new ArrayList<>(newArray.length);
        result.addAll(Arrays.asList(newArray));
        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("argument cannot be null");
        return false;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) throw new NullPointerException("argument cannot be null");
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.asList(elements).iterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return Arrays.asList(elements).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return Arrays.asList(elements).listIterator(i);
    }


    @SuppressWarnings("unchecked")
    private void grow(int length) {
        final int newLength = length > (elements.length * 2) + elements.length / 2 ? (length * 2) + length / 2 :
                (elements.length * 2) + elements.length / 2;
        T[] newElements = (T[]) new Object[newLength];
        for (int i = 0; i < size(); i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        newElements = null;
        capacity = elements.length;
    }

    public static void main(String[] args) {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>(3);
        customArrayList.add(5);
        customArrayList.add(4);
        customArrayList.add(2);
        customArrayList.add(3);
        List<Integer> newList = new ArrayList<>();
        newList.add(1);
        newList.add(1);
        newList.add(1);
        newList.add(1);
        newList.add(1);
        newList.add(1);
        newList.add(1);
        System.out.println(customArrayList);
        customArrayList.sort(Integer::compareTo);
        System.out.println("after sorting " + customArrayList);
        customArrayList.addAll(1, newList);
        System.out.println(customArrayList);
        customArrayList.forEach(el -> System.out.print(el + " "));
        System.out.println();
        System.out.println("size " + customArrayList.size());
        System.out.println("capacity " + customArrayList.getCapacity());
        customArrayList.removeIf(i -> i == 1);
        customArrayList.forEach(System.out::print);
        System.out.println();
        System.out.println("size " + customArrayList.size());
        System.out.println("capacity " + customArrayList.getCapacity());
        customArrayList.clear();
        customArrayList.add(5);
        customArrayList.add(4);
        customArrayList.add(2);
        customArrayList.add(3);
        customArrayList.add(2, 8);
        customArrayList.addAll(3, newList);
        System.out.println(customArrayList);
        System.out.println(customArrayList.remove(11));
        System.out.println(customArrayList);
        System.out.println(customArrayList.subList(2, 7));
        System.out.println("size " + customArrayList.size());
        System.out.println("capacity " + customArrayList.getCapacity());
        customArrayList.add(null);
        System.out.println(customArrayList.indexOf(1));
        System.out.println(customArrayList.lastIndexOf(1));
        System.out.println(customArrayList.contains(null));
        System.out.println(customArrayList.contains(17));
        System.out.println("size " + customArrayList.size());
        System.out.println("capacity " + customArrayList.getCapacity());
    }
}

