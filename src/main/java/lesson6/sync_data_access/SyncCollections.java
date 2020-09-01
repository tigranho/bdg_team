package lesson6.sync_data_access;

import java.util.*;

public class SyncCollections {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(5);
        integers.add(4);
        integers = Collections.synchronizedList(integers);
        for (Integer i : integers) {
            System.out.print(i + " ");
            if (i == 1) integers.remove(i); //throws ConcurrentModificationException
        }
        System.out.println();

        List<Integer> list = Collections.synchronizedList(
                new ArrayList<>(Arrays.asList(4, 3, 52)));
        synchronized (list) {
            for (int data : list) {
                System.out.print(data + " ");
                if (data == 4) list.remove(0); //throws ConcurrentModificationException
            }
        }
        System.out.println();

        Map<String, Object> foodData = new HashMap<>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);
        Map<String, Object> synchronizedFoodData = Collections.synchronizedMap(foodData);
        for (String key : synchronizedFoodData.keySet())
            synchronizedFoodData.remove(key); //throws ConcurrentModificationException
    }
}
