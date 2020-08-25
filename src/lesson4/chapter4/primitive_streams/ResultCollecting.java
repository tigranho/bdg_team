package lesson4.chapter4.primitive_streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author Hrach
 */


public class ResultCollecting {
    public static void main(String[] args) {
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        out.println(result);

        ohMy = Stream.of("lions", "tigers", "bears");
        Double aDouble = ohMy.collect(Collectors.averagingInt(String::length));
        out.println(aDouble);

        ohMy = Stream.of("lions", "tigers", "bears");
        TreeSet<String> set = ohMy.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        out.println(set);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = ohMy.collect(
                Collectors.toMap(s -> s, String::length));
        out.println(map);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map1 = ohMy.collect(Collectors.toMap(String::length, k ->
                k, (s1, s2) -> s1, HashMap::new));
        out.println(map1);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map2 = ohMy.collect(
                Collectors.groupingBy(String::length));
        out.println(map2);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map3 = ohMy.collect(
                Collectors.groupingBy(String::length, Collectors.toSet()));
        out.println(map3);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map4 = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 5));
        out.println(map4);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map5 = ohMy.collect(Collectors.groupingBy(
                String::length, Collectors.counting()));
        out.println(map5);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map6 = ohMy.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(s -> s.charAt(0),
                                Collectors.minBy(Comparator.naturalOrder()))));
        out.println(map6);
    }
}
