import sava.util.CustomArrayList;
import sava.util.CustomLinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list1 = new CustomArrayList<>();
        List<String> list2 = new CustomLinkedList<>();

        list2.add("Samvel");
        list2.add("Armen");
        list2.add("Sargis");

        System.out.println(list2);

        list1.add(8);
        list1.add(9);
        list1.add(4);
        list1.add(9);

        list1.add(3);

        System.out.println(list1);
    }
}
