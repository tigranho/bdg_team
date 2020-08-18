package lesson2.chapter3.collections.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class SetMethods {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        out.println(set.add(66));
        out.println(set.add(10));
        out.println(set.add(66));
        out.println(set.add(8));
        for(int i: set) System.out.print(i + ",");
        out.println();

        set = new TreeSet<>();
        out.println(set.add(66));
        out.println(set.add(10));
        out.println(set.add(66));
        out.println(set.add(8));
        for(int i: set) System.out.print(i + ",");
        out.println();
    }
}
