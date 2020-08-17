package lesson2.chapter3.collections;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class CollectionMethods {
    public static void main(String[] args) {
        Collection<String> birds = new ArrayList<>();
        birds.add("hawk");
        add(birds, "hawk");
        remove(birds, "hawk");
        remove(birds, "cardinal");
        birds.add("nency");
        remove(birds, 6);
        size(birds);
        isEmpty(birds);
        clear(birds);
        isEmpty(birds);
        size(birds);
        contains(birds, "cardinal");
        contains(birds, "nency");
    }

    static <T> void add(Collection<T> list, T t) {
        out.println(list.add(t));
    }

    static <T> void remove(Collection<T> list, T t) {
        out.println(list.remove(t));
    }

    static <T> void remove(Collection<T> list, int idx) {
        out.println(list.remove(idx));
    }

    static <T> void size(Collection<T> list) {
        out.println(list.size());
    }

    static <T> void isEmpty(Collection<T> list) {
        out.println(list.isEmpty());
    }

    static <T> void clear(Collection<T> list) {
        list.clear();
    }

    static <T> void contains(Collection<T> list, T t) {
        out.println(list.contains(t));
    }
}
