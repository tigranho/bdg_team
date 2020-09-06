import sam.util.CustomArrayList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new CustomArrayList<>();

        list.add(8);
        list.add(9);
        list.add(11);
        list.add(3);

        System.out.println(list);
        System.out.println(list.get(3));

        list.remove(new Integer(11));
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        System.out.println(list.indexOf(3));
    }
}
