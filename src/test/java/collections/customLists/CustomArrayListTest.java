package collections.customLists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    
    @Test
    public void testListInit(){
        List<Object> list = new CustomArrayList<>();
        Assertions.assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);

        list.add("");
        Assertions.assertFalse(list.isEmpty());
        assertFalse(list.size() == 0);
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
    public void testInvalidCapacity(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<String> list = new CustomArrayList<String>(-1);
        });

    }

    @Test
    public void testAddElements(){
        List<String> list = new CustomArrayList<String>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Karol", list.get(0));
        assertEquals("Vanessa", list.get(1));
        assertEquals("Amanda", list.get(2));

        list.add(1, "Mariana");

        assertEquals("Karol", list.get(0));
        assertEquals("Mariana", list.get(1));
        assertEquals("Vanessa", list.get(2));
        assertEquals("Amanda", list.get(3));

        assertTrue(list.size()==4);
    }

    @Test
    public void testSetElement(){
        List<String> list = new CustomArrayList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        list.set(1, "Livia");

        assertEquals("Karol", list.get(0));
        assertEquals("Livia", list.get(1));
        assertEquals("Amanda", list.get(2));
    }

    @Test
    public void testRemoveElement(){
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Amanda", list.remove(2));
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
    public void whenListIsEmpty_theIteratorNextThrowsException(){
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            List<String> list = new CustomArrayList<>();
            Iterator<String> iterator = list.iterator();
            iterator.next();
        });

    }

    @Test
    public void whenListIsNotEmpty_theIteratorNextReturnsNextElement(){
        List<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(),"A");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(),"B");
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