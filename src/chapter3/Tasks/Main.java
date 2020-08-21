package chapter3.Tasks;

import chapter3.Tasks.CustomLists.ArrayLists.CustomArrayList;
import chapter3.Tasks.CustomLists.LinkedLists.CustomLinkedList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> arrayList = new CustomArrayList<>();

        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);

        System.out.println(arrayList);

        Integer integer = arrayList.get(3);
        System.out.println(integer);

        Integer remove = arrayList.remove(2);
        System.out.println(arrayList);
        arrayList.set(1, 60);
        System.out.println(arrayList);

        System.out.println("CustomLinkedList");
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
    }
}