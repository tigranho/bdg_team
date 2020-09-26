package collections;

import collections.customLists.CustomArrayList;
import collections.customLists.CustomLinkedList;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("CustomArrayList");
        List<Integer> arrayList = new CustomArrayList<>();

        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);

        System.out.println(arrayList);

        int size = arrayList.size();
        System.out.println(size);

        Integer integer = arrayList.get(3);
        System.out.println(integer);

        arrayList.remove(2);
        System.out.println(arrayList);

        arrayList.set(1, 60);
        System.out.println(arrayList);

        System.out.println(arrayList.size());

        System.out.println("\nCustomLinkedList");
        List<String> linkedList = new CustomLinkedList<>();

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");

        System.out.println(linkedList);

        String s = linkedList.get(3);
        System.out.println(s);

        linkedList.set(2, "10");
        System.out.println(linkedList);

        linkedList.remove(0);
        System.out.println(linkedList);

        System.out.println("\nCustomHashMap");
        Map<Integer, String> map = new CustomHashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");

        System.out.println(map);

        String s1 = map.get(5);
        System.out.println(s1);

        boolean b = map.containsKey(2);
        System.out.println(b);

        boolean empty = map.isEmpty();
        System.out.println(empty);

        int size1 = map.size();
        System.out.println(size1);

        map.clear();

        boolean empty1 = map.isEmpty();
        System.out.println(empty1);
    }
}