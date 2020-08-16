package lesson2.chapter3.array_and_arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class ArrayAndArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Fluffy");
        list.add("Webby");
        String[] array = new String[list.size()];
        array[0] = list.get(1);
        array[1] = list.get(0);

        array = new String[]{"gerbil", "mouse"};
        list = Arrays.asList(array);
        list.set(1, "test");
        array[0] = "new";
        String[] array2 =  list.toArray(String[]::new);
        Arrays.stream(array2).forEach(out::print);
        out.println();
        list.remove(1);

        //searching and sorting
        int[] numbers = {6, 9, 1, 8};
        Arrays.sort(numbers);
        out.println(Arrays.binarySearch(numbers, 6));
        out.println(Arrays.binarySearch(numbers, 7));

        List<Integer> nums = Arrays.asList(9, 7, 5, 3);
        Collections.sort(nums);
        out.println(Collections.binarySearch(nums, 3));
        out.println(Collections.binarySearch(nums, 2));

    }
}
