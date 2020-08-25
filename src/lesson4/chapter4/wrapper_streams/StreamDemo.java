package lesson4.chapter4.wrapper_streams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * @author Hrach
 */

public class StreamDemo {
    public static void main(String[] args) {
        try(Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5)) {
        integerStream.map(n -> n << 2).onClose(() ->
                System.out.println("stream are closed"))
                .forEach(out::println);
//        out.println(integerStream.count());
        }
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        oddNumbers.forEach(out::println);
    }
}
