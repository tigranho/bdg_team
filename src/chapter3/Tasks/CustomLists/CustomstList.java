package chapter3.Tasks.CustomLists;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface CustomstList<T> extends List<T> {
    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(Object o);

    @Override
    Iterator<T> iterator();

    @Override
    Object[] toArray();

    @Override
    <T1> T1[] toArray(T1[] a);

    @Override
    boolean add(T t);

    @Override
    boolean remove(Object o);

    @Override
    boolean containsAll(Collection<?> c);

    @Override
    boolean addAll(Collection<? extends T> c);

    @Override
    boolean addAll(int index, Collection<? extends T> c);

    @Override
    boolean removeAll(Collection<?> c);

    @Override
    boolean retainAll(Collection<?> c);

    @Override
    default void replaceAll(UnaryOperator<T> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<T> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    @Override
    default void sort(Comparator<? super T> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<T> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((T) e);
        }
    }

    @Override
    void clear();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    T get(int index);

    @Override
    T set(int index, T element);

    @Override
    void add(int index, T element);

    @Override
    T remove(int index);

    @Override
    int indexOf(Object o);

    @Override
    int lastIndexOf(Object o);

    @Override
    ListIterator<T> listIterator();

    @Override
    ListIterator<T> listIterator(int index);

    @Override
    List<T> subList(int fromIndex, int toIndex);

    @Override
    default Spliterator<T> spliterator() {
        return null;
    }

    @Override
    default <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }

    @Override
    default boolean removeIf(Predicate<? super T> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<T> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }


}