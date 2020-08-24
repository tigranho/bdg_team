package lesson4.chapter4.optional;

import java.util.Optional;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class OptionalDemo {

    public static Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        else {
            int sum = 0;
            for(int score: scores) sum += score;
            return Optional.of((double) sum / scores.length);
        }
    }

    public static void main(String[] args) {
        out.println(average(90, 100));
        out.println(average());
        Optional<Double> opt = average(90, 100);
        opt.ifPresent(out::println);

        opt = average();
        out.println(opt.orElse(Double.NaN));
        out.println(opt.orElseGet(Math::random));
        out.println(opt.orElseThrow(IllegalStateException::new));
//        out.println(opt.orElseGet(IllegalStateException::new));
    }
}
