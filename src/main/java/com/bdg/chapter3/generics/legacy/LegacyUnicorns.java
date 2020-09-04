package main.java.chapter3.generics.legacy;

import java.util.List;

/**
 * @author Tigran
 */
public class LegacyUnicorns {
    public static void main(String[] args) {
        java.util.List<Unicorn> unicorns = new java.util.ArrayList<>();
        addUnicorn(unicorns);
        Unicorn unicorn = unicorns.get(0); // ClassCastException
    }

    private static void addUnicorn(List unicorn) {
        unicorn.add(new Dragon());
    }
}
