package lesson2.chapter3.collections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hrach
 */

public class ListMethods {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("SD");
        list.add(0, "NY");
        list.set(1, "FL");
        list.remove("NY");
        System.out.println(list.remove(0));
        list.add("CO");
        list.add("OH");
        list.add("NJ");
        String state = list.get(0);
        int idx = list.indexOf("NJ");

        for (String string: list) {
            System.out.println(string);
        }
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()) {
            String string = iter.next();
            System.out.println(string);
        }
    }
}
