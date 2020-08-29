package com.bdg.chapter3.searching_sorting;


import com.bdg.chapter3.comparable.Duck;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Tigran
 */
public class UseTreeSet {
    static class Rabbit {
        int id;
    }

    public static void main(String[] args) {
        Set<Duck> ducks = new TreeSet<>();
        ducks.add(new Duck("Puddles"));
        Set<Rabbit> rabbit = new TreeSet<>();
        rabbit.add(new Rabbit()); // throws an exception

//        Set<Rabbit> rabbit = new TreeSet<>(new Comparator<Rabbit>() {
//            public int compare(Rabbit r1, Rabbit r2) {
//                return r1.id = r2.id;
//            }
//        });
//        rabbit.add(new Rabbit());
    }
}

