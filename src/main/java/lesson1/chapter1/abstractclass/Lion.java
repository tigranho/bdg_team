package lesson1.chapter1.abstractclass;

/**
 * @author Tigran
 */
public class Lion extends BigCat {
    public static void main(String[] args) {
        System.out.println(new Lion().supplier.get());
        System.out.println(new Lion().supplier.getClass());
    }
}
