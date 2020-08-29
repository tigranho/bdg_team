package com.bdg.chapter3.method_references;

import com.bdg.chapter3.comparator.Duck;

import java.util.*;
import java.util.function.BiFunction;

/**
 * @author Tigran
 */
public class DuckHelper {
    public static int compareByWeight(Duck d1, Duck d2) {
        return d1.getWeight() - d2.getWeight();
    }

    public static int compareByName(Duck d1, Duck d2) {
        return d1.getName().compareTo(d2.getName());
    }

    public static void main(String[] args) {
        Comparator<Duck> byWeight = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);

        Comparator<Duck> byWeight1 = DuckHelper::compareByWeight;


        ////////////////////////////////////////////////////////////

        List<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list); // [Magician, Assistant]
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list); // [Magician]


        //////////////////////////////////////////////////

        List<Integer> integerList = Arrays.asList(1, 2, 3);
        integerList.replaceAll(x -> x * 2);
        System.out.println(integerList); // [2, 4, 6]


        merge();
    }

     static  void merge() {
        BiFunction<String, String, String> mapper = (v1, v2)
                -> v1.length() > v2.length() ? v1 : v2;

        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");

        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);

        System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
        System.out.println(jenny); // Bus Tour
        System.out.println(tom); // Skyride
    }
}

