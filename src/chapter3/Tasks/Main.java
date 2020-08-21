package chapter3.Tasks;

import chapter3.Tasks.CustomLists.ArrayLists.CustomArrayList;
import chapter3.Tasks.CustomLists.CustomstList;
import chapter3.Tasks.CustomLists.LinkedLists.CustomLinkedList;

public class Main {
    public static void main(String[] args) {

        CustomstList<Integer> arrayList = new CustomArrayList<>();

        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);

        System.out.println(arrayList);

        Integer integer = arrayList.get(3);
        System.out.println(integer);

        CustomstList<String> linkedList = new CustomLinkedList<>();

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");

        System.out.println(linkedList);

        String s = linkedList.get(3);
        System.out.println(s);
    }
}