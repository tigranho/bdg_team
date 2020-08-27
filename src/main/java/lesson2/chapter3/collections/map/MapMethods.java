package lesson2.chapter3.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class MapMethods {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        out.println(map.get("koala"));
        map.keySet().forEach(key -> out.printf("%s,", key));

        map = new TreeMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        out.println(map.get("koala"));
        map.keySet().forEach(key -> out.printf("%s,", key));

        out.println(map.containsKey("lion"));
        out.println(map.containsValue("lion"));
        out.println(map.size());
    }
}
