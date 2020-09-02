package lesson6.parallel_stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingByConcurrent;

public class ParallelReduction {
    public static void main(String[] args) {
//        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
//                .parallelStream()
//                .reduce("", (c, s1) -> c + s1, String::concat));
//
//        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6)
//                .parallelStream()
//                .reduce(0, (a, b) -> (a - b)));
//
//        System.out.println(Arrays.asList("w", "o", "l", "f")
//                .parallelStream()
//                .reduce("X", String::concat));

//        Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
//        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new, Set::add,
//                Set::addAll);
//        System.out.println(set);

//        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
//        ConcurrentMap<Integer, String> map = ohMy
//                .collect(Collectors.toConcurrentMap(String::length, k -> k,
//                        (s1, s2) -> s1 + "," + s2));
//        System.out.println(map);
//        System.out.println(map.getClass());

        Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> map = ohMy.collect(
                groupingByConcurrent(String::length));
        System.out.println(map);
    }
}
