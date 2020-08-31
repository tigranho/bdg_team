import CustomCollections.CustomList.CustomArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    @Test
    public void testListInit() {
        List<Object> list = new CustomArrayList<>();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);

        List<Integer> list1 = new CustomArrayList<>(List.of(1, 2, 3));
        assertFalse(list1.isEmpty());
        assertEquals(list1.size(), 3);
    }

    @Test
    public void testAdd() {
        List<String> list = new CustomArrayList<>();
        list.add("hi");
        list.add("hello");
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), "hi");
        assertEquals(list.get(1), "hello");
    }

    @Test
    public void testAdd1() {
        List<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0, 3);
        assertEquals(list.get(0), 3);
        assertEquals(list.get(2), 2);
    }

    @Test
    public void testSet() {
        List<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.set(0, 15);
        assertEquals(list.get(0), 15);
    }

    @Test
    public void testRemove() {
        List<String> list = new CustomArrayList<>();
        list.add("wind");
        list.add(0, "cold");
        list.add(0, "warm");
        list.remove(0);
        list.remove("cold");
        assertEquals(list.get(0), "wind");
        assertEquals(list.size(), 1);
    }

    @Test
    public void testRemoveWithEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Object> list = new CustomArrayList<>();
            list.remove(0);
        });
    }

    @Test
    public void testIteratorWithEmpty() {
        List<String> list = new CustomArrayList<>();
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIterator() {
        List<Integer> list = new CustomArrayList<>(List.of(1, 2, 3));
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), 1);
        assertEquals(iterator.next(), 2);
        assertEquals(iterator.next(), 3);
    }

    @Test
    public void whenListIsEmpty_theIteratorNextThrowsException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            List<String> list = new CustomArrayList<>();
            Iterator<String> iterator = list.iterator();
            iterator.next();
        });
    }
}
