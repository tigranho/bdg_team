package collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import sam.util.CustomArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    public void testListInit() {
        List<Object> list = new CustomArrayList<>();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    @Test
    public void givenNonEmptyList_whenIsEmpty_thenFalseIsReturned() {
        List<Object> list = new CustomArrayList<>();
        list.add(null);

        assertFalse(list.isEmpty());
    }

    @Test
    public void givenEmptyList_whenElementIsAdded_thenGetReturnsThatElement() {
        List<Object> list = new CustomArrayList<>();
        boolean succeeded = list.add(null);
        assertTrue(succeeded);
    }

    @Test
    public void testInvalidCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<String> list = new CustomArrayList<>(-1);
        });
    }

    @Test
    public void testAddElements() {
        List<String> list = new ArrayList<>();
        list.add(0, "Samvel");
        list.add(1, "Karen");
        list.add(2, "Sargis");

        assertEquals("Samvel", list.get(0));
        assertEquals("Karen", list.get(1));
        assertEquals("Sargis", list.get(2));

        list.add(1, "Mariam");

        assertEquals("Samvel", list.get(0));
        assertEquals("Mariam", list.get(1));
        assertEquals("Karen", list.get(2));
        assertEquals("Sargis", list.get(3));
    }

    @Test
    public void testSetElement() {
        List<String> list = new ArrayList<>();
        list.add(0, "Samvel");
        list.add(1, "Karen");
        list.add(2, "Sargis");

        list.set(1, "Vardan");

        assertEquals("Samvel", list.get(0));
        assertEquals("Vardan", list.get(1));
        assertEquals("Sargis", list.get(2));

    }

    @Test
    public void testRemoveElement() {
        List<String> list = new ArrayList<>();
        list.add(0, "Samvel");
        list.add(1, "Karen");
        list.add(2, "Sargis");

        assertEquals("Sargis", list.remove(2));
        assertTrue(list.size() == 2);
    }

    @Test
    public void testRemoveWithEmptyList(){
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Object> list = new CustomArrayList<>();
            list.remove(0);
        });
    }

    @Test
    public void whenListIsEmpty_theIteratorHasNextReturnsFalse(){
        List<String> list = new CustomArrayList<>();
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenListIsNotEmpty_theIteratorNextReturnsNextElement() {
        List<String> list = new CustomArrayList<>();
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
    public void listIteratorShouldBeFailFast(){
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            List<String> list = new CustomArrayList<>();
            list.add("A");
            list.add("B");
            Iterator<String> iterator = list.iterator();
            iterator.next();
            list.remove(0);
            iterator.next();
        });
    }
}
