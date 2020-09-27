package collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapTest {

    @Test
    void notEquals() {

        Map<String, Integer> map = new CustomHashMap<>();
        map.put("A", 1);
        map.put("B", 2);

        assertNotEquals("A", map.get(1));
    }

    @Test
    public void testMapInit() {
        Map<String, Integer> map = new CustomHashMap<>();
        Assertions.assertTrue(map.isEmpty());
        assertEquals(0, 0);

        map.put("", 1);
        Assertions.assertFalse(map.isEmpty());
        assertNotEquals(map.size(), 0);
    }

    @Test
    public void givenNonEmptyMap_whenIsEmpty_thenFalseIsReturned() {
        Map<String, String> map = new CustomHashMap<>();
        map.put("A", "A");

        assertFalse(map.isEmpty());
    }

    @Test
    public void testAddElements() {
        Map<Object, Object> map = new CustomHashMap<>();
        map.put("Karol", 0);
        map.put("Vanessa", 1);
        map.put("Amanda", 2);

        assertEquals(0, map.get("Karol"));
        assertEquals(1, map.get("Vanessa"));
        assertEquals(2, map.get("Amanda"));

        map.put("Mariana", 0);

        assertEquals(0, map.get("Mariana"));

        assertEquals(map.size(), 4);
    }

    @Test
    public void testSetElement() {
        Map<String, Integer> map = new CustomHashMap<>();
        map.put("Karol", 1);
        map.put("Vanessa", 2);
        map.put("Amanda", 3);

        map.put("Livia", 2);

        assertEquals(1, map.get("Karol"));
        assertEquals(2, map.get("Livia"));
        assertEquals(3, map.get("Amanda"));
    }

    @Test
    public void testRemoveElement() {
        Map<String, Integer> map = new CustomHashMap<>();
        map.put("K", 0);
        map.put("V", 1);
        map.put("A", 2);

        assertFalse(map.remove("k", 0));
        assertEquals(map.size(), 3);
    }

    @Test
    public void testRemoveWithEmptyMap() {
        Assertions.assertThrows(Throwable.class, () ->{
            Map<Object, Object> map = new CustomHashMap<>();
            assertFalse((boolean) map.remove(null));
        });
    }
}