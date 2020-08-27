package lesson4.chapter4.primitive_streams;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.System.out;

/**
 * @author Hrach
 */


public class PrimitiveOptional {
    public static void main(String[] args) {
        IntStream stream = IntStream.rangeClosed(1,10);
        OptionalDouble optional = stream.average();
        optional.ifPresent(out::println);
        out.println(optional.getAsDouble());
        out.println(optional.orElseGet(() -> Double.NaN));

        LongStream longs = LongStream.of(5, 10);
        long sum = longs.sum();
        out.println(sum);

        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
        OptionalDouble min = doubles.min();
        min.ifPresent(out::println);
    }

    private static void threeDigit(Optional<Integer> optional) {
        optional.map(n -> "" + n)
                .filter(s -> s.length() == 3)
                .ifPresent(out::println);
    }
}
