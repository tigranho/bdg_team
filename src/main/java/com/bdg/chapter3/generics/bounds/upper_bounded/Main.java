package com.bdg.chapter3.generics.bounds.upper_bounded;

import java.util.ArrayList;
import java.util.List;

public class Main<T> {

    static class Sparrow extends Bird {
    }

    static class Bird {
    }

    public static void main(String[] args) {
        List<? extends Bird> birds = new ArrayList<Bird>();
       // birds.add(new Sparrow()); // DOES NOT COMPILE
       // birds.add(new Bird()); // DOES NOT COMPILE
    }

    public static long total(List<? extends Number> list) {
        long count = 0;
        for (Number number: list)
            count += number.longValue();
        return count;
    }


    private void anyFlyer(List<Flyer> flyer) {}

    private void groupOfFlyers(List<? extends Flyer> flyer) {}


}
