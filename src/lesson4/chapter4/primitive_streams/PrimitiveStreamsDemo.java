package lesson4.chapter4.primitive_streams;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author Hrach
 */


public class PrimitiveStreamsDemo {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble avg = intStream.average();
        avg.ifPresent(out::println);

        DoubleStream oneValue = DoubleStream.of(3.14);
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        oneValue.forEach(out::println);
        out.println();
        varargs.forEach(out::println);

        DoubleStream random = DoubleStream.generate(Math::random);
        DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
        random.limit(3).forEach(out::println);
        out.println();
        fractions.limit(3).forEach(out::println);

        IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
        count.forEach(out::println);

        IntStream range = IntStream.range(1, 6);
        range.forEach(out::println);

        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(out::println);

        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream mapToInt = objStream.mapToInt(String::length);
    }
}
