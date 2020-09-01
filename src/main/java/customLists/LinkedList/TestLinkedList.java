package customLists.LinkedList;

/**
 * @author Tatevik Mirzoyan
 */
public class TestLinkedList {
    public static void main(String[] args) {
        MyCustomLinkedList<Integer> list = new MyCustomLinkedList<>();
        list.add(12);
        list.add(15);
        list.add(34);
        System.out.println(list.toString());
        list.remove(2);
        System.out.println(list.toString());



    }
}
