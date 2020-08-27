package main.java.chapter1.enums;

public class Main {

    public static void main(String[] args) {

        OnlyOne firstCall = OnlyOne.ONCE; // prints constructing
        OnlyOne secondCall = OnlyOne.ONCE; // doesn't print anything
    }

}