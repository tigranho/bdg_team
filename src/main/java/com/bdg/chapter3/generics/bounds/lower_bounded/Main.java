package com.bdg.chapter3.generics.bounds.lower_bounded;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main<T> {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<String>();
        strings.add("tweet");
        List<Object> objects = new ArrayList<Object>(strings);
        addSound(strings);
        addSound(objects);

         List<? super IOException> exceptions = new ArrayList<Throwable>();
         //exceptions.add(new Exception()); // DOES NOT COMPILE
         exceptions.add(new IOException());
         exceptions.add(new FileNotFoundException());
    }

    public static void addSound(List<? super String> list) { // lower bound
        list.add("quack");
    }
}