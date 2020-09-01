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
        list.add("hi");
        //list.add(5, "hi + hi");

        System.out.println(list.toString());
    }
}
