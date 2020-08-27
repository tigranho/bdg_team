package lesson4.chapter4.functional_programming;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class PredicateImpl {
    public static void main(String[] args) {
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();
        out.println(p1.test(""));
        out.println(p2.test(""));

        BiPredicate<String, String> b1 = String::startsWith;
        BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
        out.println(b1.test("chicken", "chick"));
        out.println(b2.test("chicken", "chick"));

        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");
        Predicate<String> brownEggs1 = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherEggs1 = s -> s.contains("egg") && ! s.contains("brown");
        Predicate<String> brownEggs2 = egg.and(brown);
        Predicate<String> otherEggs2 = egg.and(brown.negate());
    }
}
