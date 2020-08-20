package lesson2.chapter3.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class MapStreamMethods {
    public static void main(String[] args) {
        var favorites = new HashMap<String, String>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Jenny", "Tram");
        out.println(favorites);
        out.println("-".repeat(55));

        favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", null);

        favorites.putIfAbsent("Jenny", "Tram");
        favorites.putIfAbsent("Sam", "Tram");
        favorites.putIfAbsent("Tom", "Tram");
        out.println(favorites);
        out.println("-".repeat(55));

        BiFunction<String, String, String> mapper = (v1, v2) ->
                v1.length() > v2.length() ? v1 : v2;
        favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");

        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);

        out.println(favorites);
        out.println(jenny);
        out.println(tom);
        out.println("-".repeat(55));

        favorites = new HashMap<>();
        favorites.put("Sam", null);
        favorites.merge("Tom", "Skyride", mapper);
        favorites.merge("Sam", "Skyride", mapper);
        out.println(favorites);
        out.println("-".repeat(55));

        mapper = (v1, v2) -> null;
        favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Bus Tour");
        favorites.merge("Jenny", "Skyride", mapper);
        favorites.merge("Sam", "Skyride", mapper);
        System.out.println(favorites);
        out.println("-".repeat(55));

        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        BiFunction<String, Integer, Integer> mapper1 = (k, v) -> v + 1;
        Integer jenny1 = counts.computeIfPresent("Jenny", mapper1);
        Integer sam = counts.computeIfPresent("Sam", mapper1);
        System.out.println(counts);
        System.out.println(jenny);
        System.out.println(sam);
        out.println("-".repeat(55));

        counts = new HashMap<>();
        counts.put("Jenny", 15);
        counts.put("Tom", null);
        Function<String, Integer> mapper2 = (k) -> 1;
        jenny1 = counts.computeIfAbsent("Jenny", mapper2);
        sam = counts.computeIfAbsent("Sam", mapper2);
        Integer tom1 = counts.computeIfAbsent("Tom", mapper2);
        out.println(counts);
    }
}