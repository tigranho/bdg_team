package lesson4.chapter4.wrapper_streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class TerminalOp {
    public static void main(String[] args) {
        //count
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        out.println(s.count());

        //min max
        s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(out::println);
        Optional<?> minEmpty = Stream.empty().min((s1, s2) -> s1.hashCode() - s2.hashCode());
        out.println(minEmpty.isPresent());

        //findAny findFirst
        Stream<String> s1 = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        s1.findAny().ifPresent(out::println);
        infinite.findAny().ifPresent(out::println);

        //allMatch, anyMatch and noneMatch
        List<String> list = Arrays.asList("monkey", "2", "chimp");
        infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
        out.println(list.stream().anyMatch(pred));
        out.println(list.stream().allMatch(pred));
        out.println(list.stream().noneMatch(pred));
        out.println(infinite.anyMatch(pred));

        //forEach
        s = Stream.of("Monkey", "Gorilla", "Bonobo");
        s.forEach(System.out::print);

        //reduce
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        String word = stream.reduce("", String::concat);
        out.println(word);
        Stream<Integer> stream1 = Stream.of(3, 5, 6);
        out.println(stream1.reduce(1, (a, b) -> a * b));

        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);
        empty.reduce(op).ifPresent(out::print);
        oneElement.reduce(op).ifPresent(out::print);
        threeElements.reduce(op).ifPresent(out::print);
        threeElements = Stream.of(3, 5, 6);
        out.println(threeElements.reduce(1, op, op));

        //collect
        stream = Stream.of("w", "o", "l", "f");
        StringBuilder sb = stream.collect(StringBuilder::new,
                StringBuilder::append, StringBuilder::append);
        out.println(sb);
        stream = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream.collect(
                TreeSet::new,
                TreeSet::add,
                TreeSet::addAll
        );
        out.println(set);

        stream = Stream.of("w", "o", "l", "f");
        TreeSet<String> set1 = stream.collect(Collectors.toCollection(TreeSet::new));
        out.println(set1);
        stream = Stream.of("w", "o", "l", "f");
        Set<String> set2 = stream.collect(Collectors.toSet());
        out.println(set2);
    }
}
