package lesson2.chapter3.collections.set;

import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class NavigableSetMethods {
    public static void main(String[] args) {
        NavigableSet<Integer> set = IntStream.range(1, 21).collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        out.println(set.lower(10));
        out.println(set.floor(10));
        out.println(set.ceiling(20));
        out.println(set.higher(20));
    }
}
