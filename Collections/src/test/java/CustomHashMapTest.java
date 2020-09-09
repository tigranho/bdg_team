import CustomCollections.CustomHashMap;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {
    @Test
    public void testHashMapInit() {
        Map<Object, Object> hashMap = new CustomHashMap<>();
        assertTrue(hashMap.isEmpty());
        assertTrue(hashMap.size() == 0);

        Map<String, Integer> newHashMap = new CustomHashMap<>(Map.of("one", 1, "two", 2, "three", 3));
        assertFalse(newHashMap.isEmpty());
        assertEquals(newHashMap.size(), 3);
    }

    @Test
    public void testPutAndGet() {
        Map<String, String> hashMap = new CustomHashMap<>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "3");

        assertFalse(hashMap.isEmpty());
        assertEquals(hashMap.get("a"), "1");
        assertEquals(hashMap.get("b"), "2");
        assertEquals(hashMap.get("c"), "3");
    }

    @Test
    public void testPutAll() {
        Map<String, Integer> hashMap = new CustomHashMap<>();
        hashMap.putAll(Map.of("a", 1, "b", 2, "c", 3));

        assertFalse(hashMap.isEmpty());
        assertEquals(hashMap.get("a"), 1);
        assertEquals(hashMap.get("b"), 2);
        assertEquals(hashMap.get("c"), 3);
        assertNull(hashMap.get("d"));
        assertNull(hashMap.get(1));
    }

    @Test
    public void testRemove() {
        Map<String, Integer> newHashMap = new CustomHashMap<>(Map.of("one", 1, "two", 2, "three", 3));
        newHashMap.remove("one");
        newHashMap.remove("two");
        assertEquals(newHashMap.get("three"), 3);
        assertNull(newHashMap.get("one"));
        assertNull(newHashMap.get("two"));
        assertEquals(newHashMap.size(), 1);
    }

    @Test
    public void testClear() {
        Map<String, String> newHashMap = new CustomHashMap<>(Map.of("Armenia", "Yerevan", "Croatia", "Zagreb", "Greece", "Athens"));
        newHashMap.clear();
        assertNull(newHashMap.get("Greece"));
        assertTrue(newHashMap.isEmpty());
        assertEquals(newHashMap.size(), 0);
    }

    @Test
    public void testContainsKey() {
        Map<String, String> newHashMap = new CustomHashMap<>(Map.of("Armenia", "Yerevan", "USA", "Washington", "Greece", "Athens"));
        assertTrue(newHashMap.containsKey("Greece"));
        assertTrue(newHashMap.containsKey("Armenia"));
        assertFalse(newHashMap.containsKey("Croatia"));
    }

    @Test
    public void testContainsValue() {
        Map<Integer, String> newHashMap = new CustomHashMap<>(Map.of(1, "Italy", 2, "France", 3, "Spain"));
        assertTrue(newHashMap.containsValue("Italy"));
        assertTrue(newHashMap.containsValue("Spain"));
        assertFalse(newHashMap.containsValue("Germany"));
    }

    @Test
    public void testKeySet() {
        Map<String, String> newHashMap = new CustomHashMap<>(Map.of("Armenia", "Yerevan", "USA", "Washington", "Greece", "Athens"));
        Set<String> setOfKeys = newHashMap.keySet();
        assertTrue(setOfKeys.contains("Armenia"));
        assertTrue(setOfKeys.contains("USA"));
    }

    @Test
    public void testValues() {
        Map<Integer, String> newHashMap = new CustomHashMap<>(Map.of(1, "Italy", 2, "France", 3, "Spain"));
        Collection<String> values = newHashMap.values();
        assertTrue(values.contains("Italy"));
        assertTrue(values.contains("France"));
        assertFalse(values.contains("USA"));
    }
}
