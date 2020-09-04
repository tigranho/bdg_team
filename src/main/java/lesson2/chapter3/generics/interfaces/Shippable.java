package lesson2.chapter3.generics.interfaces;

public interface Shippable<T> {
    void ship(T t);
    private static void print() {
        System.out.println("in Shippable");
    }
    default void printInfo() {
        print();
    }
}
