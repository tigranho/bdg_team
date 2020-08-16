package lesson2.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hrach
 */

public class ArrayAndArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Fluffy");
        list.add("Webby");
        String[] array = new String[list.size()];
        array[0] = list.get(1);
        array[1] = list.get(0);

        array = new String[]{"gerbil", "mouse"};
        list = Arrays.asList(args);
        list.set(1, "test");
        array[0] = "new";
        String[] array2 = (String[]) list.toArray();
        list.remove(1);
    }
}
