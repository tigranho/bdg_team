package lesson4.chapter4.functional_programming;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class UnaryOperatorImpl {
    public static void main(String[] args) {
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();
        out.println(u1.apply("chirp"));
        out.println(u2.apply("chirp"));

        BinaryOperator<String> b1 = String::concat;
        BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);
        out.println(b1.apply("baby ", "chick"));
        out.println(b2.apply("baby ", "chick"));
    }
}
