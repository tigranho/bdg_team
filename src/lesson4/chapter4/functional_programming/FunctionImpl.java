package lesson4.chapter4.functional_programming;

import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class FunctionImpl {
    public static void main(String[] args) {
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();
        out.println(f1.apply("cluck"));
        out.println(f2.apply("cluck"));

        BiFunction<String, String, String> b1 = String::concat;
        BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);
        out.println(b1.apply("baby ", "chick"));
        out.println(b2.apply("baby ", "chick"));
    }
}
