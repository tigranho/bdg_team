package tasks.customLists.customLinkedList;

import java.util.List;

/**
 * @author Tatevik Mirzoyan
 */
public class TestLinkedList {
    public static void main(String[] args) {
        List<Integer> list = new CustomLinkedList<>();
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(16);
        list.add(125);
        list.add(1243);
        list.add(1234234);

        System.out.println(list.toString());
        System.out.println(list.remove(2));
        System.out.println(list.toString());




    }
}
