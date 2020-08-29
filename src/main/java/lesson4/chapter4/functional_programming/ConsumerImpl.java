package lesson4.chapter4.functional_programming;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.*;

/**
 * @author Hrach
 */

public class ConsumerImpl {
    public static void main(String[] args) {
        Consumer<String> c1 = out::println;
        Consumer<String> c2 = x -> out.println(x);
        c1.accept("Annie");
        c2.accept("Annie");

        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        out.println(map);

        Map<String, String> map1 = new HashMap<>();
        BiConsumer<String, String> b3 = map1::put;
        BiConsumer<String, String> b4 = (k, v) -> map1.put(k, v);
        b3.accept("chicken", "Cluck");
        b4.accept("chick", "Tweep");
        out.println(map1);
    }
}