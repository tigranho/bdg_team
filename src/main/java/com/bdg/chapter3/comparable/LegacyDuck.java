package com.bdg.chapter3.comparable;

/**
 * @author Tigran
 */
public class LegacyDuck implements Comparable {
    private String name;

    public int compareTo(Object obj) {
        LegacyDuck d = (LegacyDuck) obj; // cast because no generics
        return name.compareTo(d.name);
    }
}