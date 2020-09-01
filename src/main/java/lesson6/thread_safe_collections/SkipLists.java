package lesson6.thread_safe_collections;

import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class SkipLists {
    public static void main(String[] args) {
        NavigableSet<Integer> set = new  ConcurrentSkipListSet<>();
        set.add(3);
        set.add(6);
        set.add(1);
        set.add(4);
        set.add(2);
        set.add(5);
        for (Integer i : set) {
            System.out.print(i + " ");
            if (i == 1) set.remove(i);
        }
        System.out.println();

        NavigableMap<String, Integer> map = new ConcurrentSkipListMap<>();
        map.put("e", 5);
        map.put("a", 1);
        map.put("d", 4);
        map.put("c", 3);
        map.put("f", 6);
        map.put("b", 2);
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            if (entry.getValue() == 1) map.remove(entry.getKey());
        }
    }
}
