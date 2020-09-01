package lesson6.thread_safe_collections;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWrites {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
        for (Integer item : list) {
            System.out.print(item + " ");
            list.add(list.indexOf(item), 9);
        }
        System.out.println();
        list.forEach(el -> System.out.print(el + " "));
        System.out.println("Size: " + list.size());
    }
}
