package lesson2.chapter3.wrapper_classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hrach
 */

public class Wrappers {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(new Integer(3));
        nums.add(new Integer(5));
        nums.remove(1);
        nums.remove(new Integer(5));
        System.out.println(nums);
    }
}
