package chapter3.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tigran
 */
public class Duck implements Comparable<Duck> {
    private String name;

    public Duck(String name) {
        this.name = name;
    }

    public String toString() { // use readable output
        return name;
    }

    public int compareTo(Duck d) {
        return name.compareTo(d.name); // call String's compareTo
    }

    public static void main(String[] args) {

    }
}
