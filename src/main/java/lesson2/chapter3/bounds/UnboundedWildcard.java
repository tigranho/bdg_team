package lesson2.chapter3.bounds;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hrach
 */

public class UnboundedWildcard {
    public static void printList(List<?> list) {
        for(Object obj: list) System.out.println(obj);
    }

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        printList(keywords);
//        List<Object> l = new ArrayList<String>();
        Object[] objects = new String[1];
        Integer[] numbers = { new Integer(42)};
        objects = numbers;
        objects[0] = "forty two";
    }
}
