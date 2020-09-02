package com.bdg.chapter7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tigran
 */
public class ZooManager {
    private Map<String, Object> foodData = new HashMap<String, Object>();

    public synchronized void put(String key, String value) {
        foodData.put(key, value);
    }

    public synchronized Object get(String key) {
        return foodData.get(key);
    }
}
