package lesson4.chapter4.wrapper_streams;

import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class IntermediateOp {
    public static void main(String[] args) {
        //filter
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.filter(x -> x.startsWith("m")).forEach(out::println);

        //distinct
        s = Stream.of("duck", "duck", "duck", "goose");
        s.distinct().forEach(out::println);

        //limit and skip
        Stream<Integer> s1 = Stream.iterate(1, n -> n + 1);
        s1.skip(5).limit(2).forEach(out::println);

        //map
        s = Stream.of("monkey", "gorilla", "bonobo");
        s.map(String::length).forEach(out::print);
        out.println();

        //flatMap
        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);
        animals.flatMap(Collection::stream).forEach(out::println);

        //sorted
        s = Stream.of("brown-", "bear-");
        s.sorted().forEach(out::print);
        s = Stream.of("brown bear-", "grizzly-");
        s.sorted(Comparator.reverseOrder()).forEach(out::println);

        //peek
        Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream.filter(str -> str.startsWith("g"))
                .peek(out::println).count();
        out.println(count);
        List<Integer> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');
        Stream<List<?>> stream1 = Stream.of(numbers, letters);
        stream1.map(List::size).forEach(out::print);
        StringBuilder builder = new StringBuilder();
        Stream<List<?>> good = Stream.of(numbers, letters);
        good.peek(builder::append)
                .map(List::size)
                .forEach(out::print);
        out.println(builder);
        Stream<List<?>> bad = Stream.of(numbers, letters);
        bad.peek(l -> l.remove(0)).map(List::size).forEach(out::print);
        Stream.generate(() -> "Olaf Lazisson")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);
    }
}
