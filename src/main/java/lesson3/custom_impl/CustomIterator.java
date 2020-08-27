package lesson3.custom_impl;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @param <T> access all reference types
 * @author Hrach
 */
public interface CustomIterator<T> extends Iterator<T> {

    boolean hasNext();

    T next();

    void remove();

    void forEachRemaining(Consumer<? super T> action);

}
