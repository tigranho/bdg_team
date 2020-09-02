package lesson6.parallel_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        Stream<Integer> parallelStream = stream.parallel();
        Stream<Integer> parallelStream2 = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .forEachOrdered(s -> System.out.print(s + " "));
        List<Integer> list = new CopyOnWriteArrayList<>();
        IntStream.range(0, 1 << 17).parallel().forEach(list::add);
        System.out.println(list.size());

        Arrays.asList("jackal", "kangaroo", "lemur")
                .parallelStream()
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);
        Arrays.asList("jackal", "kangaroo", "lemur")
                .parallelStream()
                .map(s -> {
                    System.out.println(s);
                    return s.toUpperCase();
                })
                .forEach(System.out::println);

        List<Integer> data = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream()
                .peek(data::add)
                .forEachOrdered(i -> System.out.print(i + " "));
        System.out.println();
        for (Integer e : data) {
            System.out.print(e + " ");
        }

        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).stream().findAny().get());
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().findAny().get());

    }
}
