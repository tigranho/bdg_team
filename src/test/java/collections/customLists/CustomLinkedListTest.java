package collections.customLists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {

    @Test
    void notEquals() {

        List<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");

        assertNotEquals("A", list.get(1));
    }

    @Test
    public void testListInit() {
        List<Object> list = new CustomLinkedList<>();
        Assertions.assertTrue(list.isEmpty());
        assertEquals(0, 0);

        list.add("");
        Assertions.assertFalse(list.isEmpty());
        assertNotEquals(list.size(), 0);
    }

    @Test
    public void givenNonEmptyList_whenIsEmpty_thenFalseIsReturned() {
        List<Object> list = new CustomLinkedList<>();
        list.add(null);

        assertFalse(list.isEmpty());
    }

    @Test
    public void givenEmptyList_whenElementIsAdded_thenGetReturnsThatElement() {
        List<Object> list = new CustomLinkedList<>();
        boolean succeeded = list.add(null);
        assertTrue(succeeded);
    }

    @Test
    public void testAddElements() {
        List<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Karol", list.get(0));
        assertEquals("Vanessa", list.get(1));
        assertEquals("Amanda", list.get(2));

        list.add(0, "Mariana");

        assertEquals("Mariana", list.get(0));
        assertEquals("Karol", list.get(1));
        assertEquals("Vanessa", list.get(2));
        assertEquals("Amanda", list.get(3));

        assertEquals(list.size(), 4);
    }

    @Test
    public void testSetElement() {
        List<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        list.set(1, "Livia");

        assertEquals("Karol", list.get(0));
        assertEquals("Livia", list.get(1));
        assertEquals("Amanda", list.get(2));
    }

    @Test
    public void testRemoveElement() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Amanda", list.remove(2));
        assertEquals(list.size(), 2);
    }

    @Test
    public void testRemoveWithEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Object> list = new CustomLinkedList<>();
            list.remove(0);
        });

    }

    @Test
    public void whenListIsNotEmpty_theIteratorNextReturnsNextElement() {
        List<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "A");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "B");
        assertFalse(iterator.hasNext());
    }

    @Test
    public void listIteratorShouldBeFailFast() {
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            List<String> list = new CustomLinkedList<>();
            list.add("A");
            list.add("B");
            Iterator<String> iterator = list.iterator();
            iterator.next();
            list.remove(0);
            iterator.next();
        });
    }
}