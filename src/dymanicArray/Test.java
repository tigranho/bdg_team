package dymanicArray;

public class Test {
    public static void main(String[] args) {
        List<Number> list = new DynamicArray<>();
        list.add(25);
        list.add(12);
        list.add(5);
        List<Integer> list1 = new DynamicArray<>();
        list1.add(25);
        list1.add(36);
        list1.add(58);
       // list.addAll(list1);
        //list.addAll(list1, 1, 2);
        System.out.println(list);
        list.insertAll(list1, 1);
        System.out.println(list);

    }
}
