import CustomCollections.CustomList.CustomLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    @Test
    public void testInit() {
        List<Object> list = new CustomLinkedList<>();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);

        List<Integer> list1 = new CustomLinkedList<>(3);
        assertFalse(list1.isEmpty());
        assertEquals(list1.size(), 1);

        List<Integer> list2 = new CustomLinkedList<>(List.of(1, 2, 3));
        assertFalse(list2.isEmpty());
        assertEquals(list2.size(), 3);
    }

    @Test
    public void testAdd() {
        List<String> list = new CustomLinkedList<>();
        list.add("hi");
        list.add("hello");
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), "hi");
        assertEquals(list.get(1), "hello");
    }

    @Test
    public void testAdd1() {
        List<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0, 5);
        list.add(2, 8);
        assertEquals(list.get(0), 5);
        assertEquals(list.get(2), 8);
        assertEquals(list.get(4), 3);
        assertEquals(list.get(3), 2);
    }

    @Test
    public void testSet() {
        List<Integer> list = new CustomLinkedList<>();
        list.add(10);
        list.set(0, 15);
        assertEquals(list.get(0), 15);
    }

    @Test
    public void testRemove() {
        List<String> list = new CustomLinkedList<>();
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
            List<Object> list = new CustomLinkedList<>();
            list.remove(0);
        });
    }

    @Test
    public void testIteratorWithEmpty() {
        List<String> list = new CustomLinkedList<>();
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIterator() {
        List<Integer> list = new CustomLinkedList<>(List.of(1, 2, 3));
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), 1);
        assertEquals(iterator.next(), 2);
        assertEquals(iterator.next(), 3);
    }

    @Test
    public void whenListIsEmpty_theIteratorNextThrowsException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            List<String> list = new CustomLinkedList<>();
            Iterator<String> iterator = list.iterator();
            iterator.next();
        });
    }
}
