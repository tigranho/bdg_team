package com.bdg.HomeWork;
import java.util.HashMap;
import java.util.Map;

public class CustomHashMap {
    public static void main (String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Armenia", 29743);
        map.put("Georgia", 69700);
        map.put("Iran", 1648000);

        System.out.println("Inputed Countries is " + map.size());
        System.out.println(map);

        if (map.containsKey("Armenia")){
            Integer a = map.get("Armenia");
            System.out.println("Territory of  Country is " + a + "km^2");

        }

        for(Map.Entry<String, Integer> e : map.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
    }
}
