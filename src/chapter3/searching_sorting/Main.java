package chapter3.searching_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main<T> {



    public static void main(String[] args) {

        int[] numbersArray = {6, 9, 1, 8};
        Arrays.sort(numbersArray); // [1,6,8,9]
        System.out.println(Arrays.binarySearch(numbersArray, 6)); // 1
        System.out.println(Arrays.binarySearch(numbersArray, 3)); // -2


    }


}
