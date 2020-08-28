import customLists.ArrayList.CustomArrayList;

import java.util.List;

/**
 * @author Tatevik Mirzoyan
 */

public class Main {
    public static void main(String[] args) {
        List<String> list = new CustomArrayList<>();
        list.add("h");
        list.add("hg");
        list.add("sdf");
        list.add(1, "g");
        list.add("sdgdfg");
        // list.add(5,"hhhhh");

        System.out.println(list.toString());
    }

}
