import customLists.LinkedList.MyCustomLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tatevik Mirzoyan
 */

public class Main {
    public static void main(String[] args) {
        List<String> listd = new MyCustomLinkedList<>();
        listd.add("java");
        listd.add("Python");
        listd.add("C++");
        System.out.println(listd.toString());
        System.out.println(listd.indexOf("C++"));
        System.out.println(listd.indexOf("java"));
        System.out.println(listd.indexOf("C"));

    }
}
