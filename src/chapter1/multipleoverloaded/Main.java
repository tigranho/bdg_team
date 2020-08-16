package chapter1.multipleoverloaded;

import javax.swing.*;
import java.util.ArrayDeque;

public class Main  {

    public static void main(String[] args) {
        new ArrayDeque<>();
        JFrame jFrame = new JFrame();
        jFrame.dispose();
       foo(Integer.parseInt(args[0]));
       Bain bain = new Bain();
    }

    private static void foo(int i) {
        System.out.print("int");
    }

    private static void foo(long i) {
        System.out.print("long");
    }

    private static void foo(Integer i) {
        System.out.print("Integer");
    }

    private static void foo(Number i) {
        System.out.print("Number");
    }

    private static void foo(int... i) {
        System.out.print("Varargs");
    }

}
class Bain {

}