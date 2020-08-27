package lesson3;

import lesson3.custom_impl.CustomIterator;
import lesson3.custom_impl.CustomLinkedList;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
//        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        CustomIterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current == 3) iterator.remove();
            System.out.print(current + " ");
        }
        System.out.println();
        iterator = list.iterator(2);
        list.remove(Integer.valueOf(1));
        iterator.forEachRemaining(System.out::println);
    }
}
