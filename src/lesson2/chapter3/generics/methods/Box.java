package lesson2.chapter3.generics.methods;

import lesson2.chapter3.generics.interfaces.Shippable;

public class Box {

    public static <T> T ship(T t) {
        System.out.println("Preparing " + t);
        return t;
    }

    public static <T> void sink(T t) {}
    public <T> void print() {}
    public static <T> T identity(T t) { return t; }
//    public static T noGood(T t) {return t;}

    public static void main(String[] args) {
        Box.<String>ship("package");
        Box.ship(args);
    }
}
