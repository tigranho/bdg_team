package main.java.chapter3.generics.legacy;

/**
 * @author Tigran
 */
public class LegacyAutoboxing {
    public static void main(String[] args) {
        java.util.List numbers = new java.util.ArrayList();
        numbers.add(5);
        //int result = numbers.get(0); // DOES NOT COMPILE
    }
}
