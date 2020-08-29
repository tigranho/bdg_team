package com.bdg.chapter1.multipleoverloaded;

public class Main  {

    public static void main(String[] args) {
       foo(7);
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
