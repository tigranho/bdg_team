package lesson1.chapter1.abstractclass;

import java.util.function.Supplier;

/**
 * @author Tigran
 */
public abstract class BigCat extends Animal {
    protected Supplier<Integer> supplier = () -> 5;
    public  String getName() {
        return "";
    }
}
