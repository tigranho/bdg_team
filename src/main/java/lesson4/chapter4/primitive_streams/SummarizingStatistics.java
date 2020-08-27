package lesson4.chapter4.primitive_streams;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.function.BooleanSupplier;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * @author Hrach
 */


public class SummarizingStatistics {
    public static void main(String[] args) {
        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > .5;
        out.println(b1.getAsBoolean());
        out.println(b2.getAsBoolean());
    }

    private static int max(IntStream ints) {
        OptionalInt optional = ints.max();
        return optional.orElseThrow(RuntimeException::new);
    }

    private static int range(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) throw new RuntimeException();
        return stats.getMax() - stats.getMin();
    }
}
