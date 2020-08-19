package lesson2.chapter3.streams;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hrach
 */

public class StreamsExamples2 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.replaceAll(x -> x * 2);
        System.out.println(list);

        List<String> cats = Arrays.asList("Annie", "Ripley");
        for (String cat : cats) {
            System.out.println(cat);
        }
        cats.forEach(System.out::println);
    }
}
