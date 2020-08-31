package lesson3.custom_impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {
    private CustomLinkedList<String> customLinkedList;

    @AfterEach
    public void setUp() {
        if (customLinkedList != null)
            customLinkedList.clear();
    }

    @Test
    public void testListInit() {
        customLinkedList = new CustomLinkedList<>();
        assertTrue(customLinkedList.isEmpty());
        assertEquals(0, customLinkedList.size());
    }

    @Test
    public void givenNonEmptyList_whenIsEmpty_thenFalseIsReturned() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(null);
        assertFalse(customLinkedList.isEmpty());
    }

    @Test
    public void givenEmptyList_whenElementIsAdded_thenGetReturnsThatElement() {
        customLinkedList = new CustomLinkedList<>();
        boolean succeeded = customLinkedList.add(null);
        assertTrue(succeeded);
    }

//    @Test
//    public void testInvalidCapacity(){
//        assertThrows(IllegalArgumentException.class, () -> {
//            customLinkedList = new CustomLinkedList<>(-1);
//        });
//    }

    @Test
    public void testAddElements() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(0, "Karol");
        customLinkedList.add(1, "Vanessa");
        customLinkedList.add(2, "Amanda");

        assertEquals("Karol", customLinkedList.get(0));
        assertEquals("Vanessa", customLinkedList.get(1));
        assertEquals("Amanda", customLinkedList.get(2));

        customLinkedList.add(1, "Mariana");

        assertEquals("Karol", customLinkedList.get(0));
        assertEquals("Mariana", customLinkedList.get(1));
        assertEquals("Vanessa", customLinkedList.get(2));
        assertEquals("Amanda", customLinkedList.get(3));

        assertEquals(4, customLinkedList.size());
    }

    @Test
    public void testSetElement() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(0, "Karol");
        customLinkedList.add(1, "Vanessa");
        customLinkedList.add(2, "Amanda");

        customLinkedList.set(1, "Livia");

        assertEquals("Karol", customLinkedList.get(0));
        assertEquals("Livia", customLinkedList.get(1));
        assertEquals("Amanda", customLinkedList.get(2));
    }

    @Test
    public void testRemoveElement() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(0, "Karol");
        customLinkedList.add(1, "Vanessa");
        customLinkedList.add(2, "Amanda");

        assertEquals("Amanda", customLinkedList.remove(2));
        assertEquals(2, customLinkedList.size());
    }

    @Test
    public void testRemoveWithEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customLinkedList = new CustomLinkedList<>();
            customLinkedList.remove(0);
        });

    }

    @Test
    public void whenListIsEmpty_theIteratorHasNextReturnsFalse() {
        customLinkedList = new CustomLinkedList<>();
        Iterator<String> iterator = customLinkedList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenListIsEmpty_theIteratorNextThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            customLinkedList = new CustomLinkedList<>();
            Iterator<String> iterator = customLinkedList.iterator();
            iterator.next();
        });

    }

    @Test
    public void whenListIsNotEmpty_theIteratorNextReturnsNextElement() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add("A");
        customLinkedList.add("B");
        Iterator<String> iterator = customLinkedList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "A");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "B");
        assertFalse(iterator.hasNext());
    }

    @Test
    public void listIteratorShouldBeFailFast() {
        assertThrows(ConcurrentModificationException.class, () -> {
            customLinkedList = new CustomLinkedList<>();
            customLinkedList.add("A");
            customLinkedList.add("B");
            Iterator<String> iterator = customLinkedList.iterator();
            iterator.next();
            customLinkedList.remove(0);
            iterator.next();
        });
    }

    @Test
    public void test_list_size_after_method_addAll() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add("a");
        customLinkedList.add("b");
        customLinkedList.add("c");
        customLinkedList.add("h");
        customLinkedList.addAll(Arrays.asList("d", "e", "f", "g"));
        assertEquals(8, customLinkedList.size());
    }

    @Test
    public void test_list_elem_after_method_addAll() {
        customLinkedList = new CustomLinkedList<>();
        customLinkedList.add("a");
        customLinkedList.add("b");
        customLinkedList.add("c");
        customLinkedList.add("d");
        customLinkedList.addAll(Arrays.asList("e", "f", "g", "h"));
        assertEquals("d", customLinkedList.get(3));
    }
}
