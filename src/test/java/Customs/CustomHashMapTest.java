package Customs;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {
    @Test
    public void testMapInit() {
        Map<String, Integer> map = new CustomHashMap<>();
        assertTrue(map.isEmpty());
        assertTrue(map.size() == 0);
    }

    @Test
    public void givenNonEmptyList_whenIsEmpty_thenFalseIsReturned() {
        Map<String, Integer> map = new CustomHashMap<>();
        map.put(null, null);

        assertFalse(map.isEmpty());
    }

    @Test
    public void testInvalidCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Map<String, Integer> map = new CustomHashMap<>(-1);
        });
    }

    @Test
    public void testGet() {
        Map<String, Integer> map = new CustomHashMap<>();
        map.put("first", 1);
        assertEquals(map.get("first"), 1);
    }

    @Test
    public void testLoadFactor() {
        Map<String, Integer> map = new CustomHashMap<>(4);
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        assertEquals(map.get("second"), 2);

        map.put("fourth", 4);

        assertEquals(map.get("fourth"), 4);
        assertEquals(map.size(), 4);
    }

    @Test
    public void testRemove() {
        Map<String, Integer> map = new CustomHashMap<>(4);
        map.put("first", 1);
        map.put("second", 2);

        assertEquals(map.remove("first"), 1);
        assertEquals(map.remove("f"), null);
    }
}