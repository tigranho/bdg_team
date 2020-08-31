package lesson3.custom_impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    private CustomArrayList<String> customArrayList;

    @AfterEach
    public void setUp() {
        if (customArrayList != null)
            customArrayList.clear();
    }

    @Test
    public void testListInit() {
        customArrayList = new CustomArrayList<>();
        Assertions.assertTrue(customArrayList.isEmpty());
        assertEquals(0, customArrayList.size());
    }

    @Test
    public void givenNonEmptyList_whenIsEmpty_thenFalseIsReturned() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add(null);
        assertFalse(customArrayList.isEmpty());
    }

    @Test
    public void givenEmptyList_whenElementIsAdded_thenGetReturnsThatElement() {
        customArrayList = new CustomArrayList<>();
        boolean succeeded = customArrayList.add(null);
        assertTrue(succeeded);
    }

    @Test
    public void testInvalidCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customArrayList = new CustomArrayList<>(-1);
        });

    }

    @Test
    public void testAddElements() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add(0, "Karol");
        customArrayList.add(1, "Vanessa");
        customArrayList.add(2, "Amanda");

        assertEquals("Karol", customArrayList.get(0));
        assertEquals("Vanessa", customArrayList.get(1));
        assertEquals("Amanda", customArrayList.get(2));

        customArrayList.add(1, "Mariana");

        assertEquals("Karol", customArrayList.get(0));
        assertEquals("Mariana", customArrayList.get(1));
        assertEquals("Vanessa", customArrayList.get(2));
        assertEquals("Amanda", customArrayList.get(3));

        assertTrue(customArrayList.size() == 4);
    }

    @Test
    public void testSetElement() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add(0, "Karol");
        customArrayList.add(1, "Vanessa");
        customArrayList.add(2, "Amanda");

        customArrayList.set(1, "Livia");

        assertEquals("Karol", customArrayList.get(0));
        assertEquals("Livia", customArrayList.get(1));
        assertEquals("Amanda", customArrayList.get(2));
    }

    @Test
    public void testRemoveElement() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add(0, "Karol");
        customArrayList.add(1, "Vanessa");
        customArrayList.add(2, "Amanda");

        assertEquals("Amanda", customArrayList.remove(2));
        assertTrue(customArrayList.size() == 2);
    }

    @Test
    public void testRemoveWithEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList = new CustomArrayList<>();
            customArrayList.remove(0);
        });

    }

    @Test
    public void whenListIsEmpty_theIteratorHasNextReturnsFalse() {
        customArrayList = new CustomArrayList<>();
        Iterator<String> iterator = customArrayList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenListIsEmpty_theIteratorNextThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            customArrayList = new CustomArrayList<>();
            Iterator<String> iterator = customArrayList.iterator();
            iterator.next();
        });

    }

    @Test
    public void whenListIsNotEmpty_theIteratorNextReturnsNextElement() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add("A");
        customArrayList.add("B");
        Iterator<String> iterator = customArrayList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "A");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "B");
        assertFalse(iterator.hasNext());
    }

    @Test
    public void listIteratorShouldBeFailFast() {
        assertThrows(ConcurrentModificationException.class, () -> {
            customArrayList = new CustomArrayList<>();
            customArrayList.add("A");
            customArrayList.add("B");
            Iterator<String> iterator = customArrayList.iterator();
            iterator.next();
            customArrayList.remove(0);
            iterator.next();
        });
    }

    @Test
    public void test_list_size_after_method_addAll() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add("a");
        customArrayList.add("b");
        customArrayList.add("c");
        customArrayList.add("h");
        customArrayList.addAll(Arrays.asList("d", "e", "f", "g"));
        assertEquals(8, customArrayList.size());
    }

    @Test
    public void test_list_elem_after_method_addAll() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add("a");
        customArrayList.add("b");
        customArrayList.add("c");
        customArrayList.add("d");
        customArrayList.addAll(Arrays.asList("e", "f", "g", "h"));
        assertEquals("d", customArrayList.get(3));
    }
}
