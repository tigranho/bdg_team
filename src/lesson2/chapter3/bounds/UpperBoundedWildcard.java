package lesson2.chapter3.bounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpperBoundedWildcard {
    public static long total(List<? extends Number> list) {
        long count = 0;
        for(Number num: list) {
            count += num.longValue();
        }
        return count;
    }

    static class Bird {}
    static class Sparrow extends Bird {}


    public static void main(String[] args) {
        Number aLong = new Long(42L);
        Number aByte = new Byte((byte) 6);
        Integer aInt = new Integer(57);
        List<? extends Number> numbers = Arrays.asList(aLong, aByte, aInt);
        System.out.println(total(numbers));
        List<? extends Bird> birds = Arrays.asList(new Bird(), new Sparrow());
        System.out.println(birds);
    }
}
