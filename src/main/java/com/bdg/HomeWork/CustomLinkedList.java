package com.bdg.HomeWork;
import java.util.LinkedList;

public class CustomLinkedList {
    public static void main(String [] args){

        LinkedList list = new LinkedList();

        list.add("Poxos");
        list.add("Petros");
        list.add("Martiros");
        list.add("Poxosyan");
        list.add("Petrosyan");
        list.add("Martirosyan");

        System.out.println("The list is " + list);

        list.add(0, "First Name - " );
        list.add(4, "Last Name - ");
        System.out.println(list);

        list.set(0,"Name - ");
        list.set(7,"Martirosov");
        System.out.println(list);
        System.out.println(list.get(1));

        // contains()

        System.out.println(list.contains("Vardush"));
        System.out.println(list.contains("Martiros"));

        // remove

        list.remove(0);
        list.remove("Last Name - ");
        System.out.println(list);

        // Iterating

        LinkedList<String> list2 = new LinkedList<>();

        list2.add("Duck");
        list2.add("Rabbit");
        list2.add(1,"Squirrel");

        for (int i = 0; i < list2.size(); i++ ){
            System.out.println(list2.get(i) + " ");
        }
        System.out.println(list2);

        for (String str:list2){
            System.out.print(str + " ");
        }



    }
}
