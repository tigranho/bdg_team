package com.bdg.customCollectionsImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

public class NewDynamicArray {

    @Test
    public void testListInit() {
        List<Object> list = new customCollections.NewDynamicArray<>();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    @Test
    public void testNonEmptyList_isEmptyMethodReturnsFalse() {
        List<Object> list = new customCollections.NewDynamicArray<>();
        list.add("ass");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddElementIntoList_addMethodRetursTrue() {
        List<Object> list = new customCollections.NewDynamicArray<>();
        boolean succeed = list.add(null);
        assertTrue(succeed);
    }

    @Test
    public void testInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<String> list = new customCollections.NewDynamicArray<>(-5);
        });
    }

    @Test
    public void testAddElements() {
        List<String> list = new customCollections.NewDynamicArray<>();
        list.add(0, "a");
        list.add(1, "b");
        list.add("as");

        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("as", list.get(2));

        list.add(1, "bb");

        assertEquals("a", list.get(0));
        assertEquals("bb", list.get(1));
        assertEquals("b", list.get(2));
        assertEquals("as", list.get(3));

        assertTrue(list.size() == 4);
    }

    @Test
    public void testSetElement() {
        List<Integer> list = new customCollections.NewDynamicArray<>();

        list.add(12);
        list.add(13);
        list.add(15);

        assertEquals(12, list.get(0));
        assertEquals(13, list.get(1));
        assertEquals(15, list.get(2));

        list.set(0, 11);

        assertEquals(11, list.get(0));
        assertEquals(13, list.get(1));
        assertEquals(15, list.get(2));
    }

    @Test
    public void testRemoveElement() {
        List<Integer> list = new customCollections.NewDynamicArray<>();

        list.add(10);
        list.add(11);
        list.add(20);

        assertEquals(11, list.remove(1));
        assertTrue(list.size() == 2);
    }

    @Test
    public void testRemoveEmptyList() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            List<Integer> list = new customCollections.NewDynamicArray<>();
            list.remove(2);
        });
    }

    @Test
    public void testWhenListIsEmpty_theIteratorsHasNextReturnsFalse() {
        List<Integer> l = new customCollections.NewDynamicArray<>();
        assertFalse(l.iterator().hasNext());
    }

    @Test
    public void testWhenListIsEmpty_theIteratorsNextMethodWillThrowException() {
        assertThrows(NoSuchElementException.class, () -> {
            List<String> list = new customCollections.NewDynamicArray<>();
            list.iterator().next();
        });
    }

    @Test
    public void testWhenListIsNotEmpty_theIteratorsHasNextMethodReturnsTrue() {
        List<Integer> list = new customCollections.NewDynamicArray<>();
        list.add(12);
        Iterator<Integer> l = list.iterator();
        assertTrue(l.hasNext());
    }

    @Test
    public void testWhenListIteratorIsNotEmpty_theIteratorsNextReturnsNextElement() {
        List<String> list = new customCollections.NewDynamicArray<>();
        list.add("a");
        list.add("asd");
        list.add("sd");
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("asd", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("sd", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void listIteratorShouldBeFailFast() {
        assertThrows(ConcurrentModificationException.class, () -> {
            List<Integer> list = new customCollections.NewDynamicArray<>();
            list.add(12);
            list.add(15);
            Iterator<Integer> l = list.iterator();
            l.next();
            list.remove(0);
            l.next();
        });
    }

    @Test
    public void testIndexOf(){
        List<Integer> list = new customCollections.NewDynamicArray<>();
        list.add(23);
        list.add(35);
        list.add(1, 52);
        assertEquals(0, list.indexOf(23));
        assertEquals(1, list.indexOf(52));
        assertEquals(2, list.indexOf(35));
    }

    @Test
    public void testLastIndexOf(){
        List<Integer> list = new customCollections.NewDynamicArray<>();
        list.add(23);
        list.add(35);
        list.add(35);
        assertEquals(2, list.lastIndexOf(35));
        assertEquals(1, list.indexOf(35));
    }

    @Test
    public void testContains(){
        List<Integer> list = new customCollections.NewDynamicArray<>();
        list.add(15);
        list.add(65);
        assertTrue(list.contains(65));
        assertTrue(list.contains(15));
        assertFalse(list.contains(644));
    }
}
