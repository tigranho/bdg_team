package lesson3.custom_impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapTest {

    private CustomHashMap<String, Integer> customHashMap;

    @AfterEach
    public void setUp() {
        if (customHashMap != null)
            customHashMap.clear();
    }

    @Test
    public void init_with_no_args_Constructor() {
        customHashMap = new CustomHashMap<>();
        assertEquals(0.75f, customHashMap.getLoadFactor());
        assertEquals(16, customHashMap.getCapacity());
    }

    @Test
    public void init_with_capacity() {
        customHashMap = new CustomHashMap<>(2 << 5);
        assertTrue(customHashMap.isEmpty());
        assertEquals(64, customHashMap.getCapacity());
    }

    @Test
    public void init_with_capacity_and_load_factor() {
        customHashMap = new CustomHashMap<>(2 << 5, 0.8f);
        assertEquals(0.8f, customHashMap.getLoadFactor());
        assertEquals(64, customHashMap.getCapacity());
    }

    @Test
    public void init_exception_when_give_illegal_arg() {
        assertThrows(IllegalArgumentException.class, () ->
                customHashMap = new CustomHashMap<>(-1));
        assertThrows(IllegalArgumentException.class, () ->
                customHashMap = new CustomHashMap<>(2 << 6, 1.1f));
    }

    @Test
    void test_put() {
        customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1); //1
        customHashMap.put("b", 2); //2
        customHashMap.put("d", 8); //3
        customHashMap.put("c", 3); //4
        customHashMap.put(null, 5); //5
        customHashMap.put("d", 4); //replace
        customHashMap.put("f", 5); //6
        customHashMap.put("g", 6); //7

        assertEquals(7, customHashMap.size());

        assertNull(customHashMap.get("z"));

        assertEquals(4, customHashMap.get("d"));

        assertEquals(5, customHashMap.get(null));

        assertEquals(10, customHashMap.getCapacity());

    }

    @Test
    void test_containsValue() {
        customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1); //1
        customHashMap.put("b", 2); //2
        customHashMap.put("d", 8); //3
        customHashMap.put("c", 3); //4
        customHashMap.put(null, 5); //5
        customHashMap.put("d", 4); //replace
        customHashMap.put("f", 5); //6
        customHashMap.put("g", null); //7
        customHashMap.put("h", 7); //8

        assertTrue(customHashMap.containsValue(7));

        assertFalse(customHashMap.containsValue(11));

        assertFalse(customHashMap.containsValue(8));

        assertTrue(customHashMap.containsValue(null));

        assertFalse(customHashMap.containsValue(6));

    }

    @Test
    void test_containsKey() {
        customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1); //1
        customHashMap.put("b", 2); //2
        customHashMap.put("d", 8); //3
        customHashMap.put("c", 3); //4
        customHashMap.put(null, 5); //5
        customHashMap.put("d", 4); //replace
        customHashMap.put("f", 5); //6
        customHashMap.put("g", 6); //7
        customHashMap.put("h", 7); //8

        assertTrue(customHashMap.containsKey("f"));

        assertFalse(customHashMap.containsKey("s"));

        assertTrue(customHashMap.containsKey(null));

        assertFalse(customHashMap.containsKey("null"));
    }

    @Test
    void test_get() {
        customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1); //1
        customHashMap.put("b", 2); //2
        customHashMap.put("d", 8); //3
        customHashMap.put("c", 3); //4
        customHashMap.put(null, 5); //5
        customHashMap.put("d", 4); //replace
        customHashMap.put("f", 5); //6
        customHashMap.put("g", 6); //7

        assertNull(customHashMap.get("z"));

        assertEquals(4, customHashMap.get("d"));

        assertNull(customHashMap.get("D"));

        assertEquals(5, customHashMap.get(null));

    }

    @Test
    void test_remove() {
        customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1); //1
        customHashMap.put("b", 2); //2
        customHashMap.put("d", 8); //3
        customHashMap.put("c", 3); //4
        customHashMap.put(null, 5); //5
        customHashMap.put("d", 4); //replace
        customHashMap.put("f", 5); //6
        customHashMap.put("g", 6); //7
        customHashMap.put("u", null); //8

        assertNull(customHashMap.remove("z"));

        assertEquals(4, customHashMap.remove("d"));

        assertNull(customHashMap.remove("u"));

        assertEquals(5, customHashMap.remove(null));

        assertEquals(5, customHashMap.size());
    }

    @Test
    void testToString() {
        customHashMap = new CustomHashMap<>();

        assertEquals("[]", customHashMap.toString());

        customHashMap.put("first", 1);
        customHashMap.put("second", 2);

        assertEquals("[first=1, second=2]", customHashMap.toString());
    }

    @Test
    public void test_clear() {
        customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1); //1
        customHashMap.put("b", 2); //2
        customHashMap.put("d", 8); //3
        customHashMap.put("c", 3); //4
        customHashMap.put(null, 5); //5
        customHashMap.put("d", 4); //replace
        customHashMap.put("f", 5); //6
        customHashMap.put("g", 6); //7
        customHashMap.put("u", null); //8

        customHashMap.clear();

        assertEquals(0, customHashMap.size());

        assertEquals("[]", customHashMap.toString());
    }
}