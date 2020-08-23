package lesson3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("one", "sunday");
        map.put("two", "tuesday");
        map.put("four", "wednesday");
        map.put("five", "thursday");
        map.put("six", "friday");
        map.put("seven", "saturday");
        map.put("zero", "sunday");
        for (Map.Entry<String, String> entry: map.entrySet()) {
            entry.setValue("monday");
        }
        System.out.println(map);
    }
}
