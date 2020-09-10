package com.bdg.customCollectionsImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;


public class NewCustomLinkedList {

    @Test
    public void testListInit(){
        List<Object> list = new customCollections.NewCustomLinkedList<>();
        assertTrue(list.size() == 0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testNonEmptyList_isEmptyMethodReturnsFalse(){
        List<Integer> list = new customCollections.NewCustomLinkedList<>();
        list.add(12);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddElementIntoList_addMethodReturnsTrue(){
        List<String> list = new customCollections.NewCustomLinkedList<>();
        assertTrue(list.add("add"));
    }

    @Test
    public void addElements(){
        List<Integer> list = new customCollections.NewCustomLinkedList<>();
        list.add(0,12);
        list.add(1, 5);
        list.add(2, 25);

        assertEquals(12, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(25, list.get(2));
        assertTrue(list.size() == 3);

        list.add(1, 36);

        assertEquals(12, list.get(0));
        assertEquals(36, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(25, list.get(3));

        assertTrue(list.size() == 4);
    }

    @Test
    public void testSetElements(){
        List<String> list = new customCollections.NewCustomLinkedList<>();
        list.add("as");
        list.add(1, "sd");
        list.add(2, "fsf");

        assertEquals("as", list.get(0));
        assertEquals("sd", list.get(1));
        assertEquals("fsf", list.get(2));

        list.set(2, "gggg");

        assertEquals("as", list.get(0));
        assertEquals("sd", list.get(1));
        assertEquals("gggg", list.get(2));
    }

    @Test
    public void testRemoveelement(){
        List<String> list = new customCollections.NewCustomLinkedList<>();
        list.add("as");
        list.add("asfsdf");

        assertEquals("as", list.remove(0));

        assertTrue(list.size() == 1);
    }

    @Test
    public void testRemoveEmptyList(){
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            List<String> list = new customCollections.NewCustomLinkedList<>();
            list.remove(0);
        });
    }

    @Test
    public void testIndexOf(){
        List<Integer> list = new customCollections.NewCustomLinkedList<>();
        list.add(23);
        list.add(35);
        list.add(1, 52);
        assertEquals(0, list.indexOf(23));
        assertEquals(1, list.indexOf(52));
        assertEquals(2, list.indexOf(35));
    }

    @Test
    public void testLastIndexOf(){
        List<Integer> list = new customCollections.NewCustomLinkedList<>();
        list.add(23);
        list.add(35);
        list.add(35);
        list.add(60);
        list.add(60);
        assertEquals(2, list.lastIndexOf(35));
        assertEquals(1, list.indexOf(35));
        assertEquals(4, list.lastIndexOf(60));

    }

    @Test
    public void testContains(){
        List<Integer> list = new customCollections.NewCustomLinkedList<>();
        list.add(15);
        list.add(65);
        assertTrue(list.contains(65));
        assertTrue(list.contains(15));
        assertFalse(list.contains(644));
    }


//    @Test
//    public void testWhenListIsEmpty_theIteratorHasNextReturnsFalse(){
//        List<Integer> list = new customCollections.NewCustomLinkedList<>();
//        Iterator iterator = list.iterator();
//        assertFalse(iterator.hasNext());
//    }

//    @Test
//    public void testWhenListIteratorIsEmpty_theIteratorNextReturnsException(){
//        assertThrows(NoSuchElementException.class, ()->{
//           List<Integer> list = new customCollections.NewCustomLinkedList<>();
//           Iterator i = list.iterator();
//           i.next();
//        });
//    }

//    @Test
//    public void testWhenListIsNotEmpty_theIteratorNextReturnsNext(){
//        List<Integer> list = new customCollections.NewCustomLinkedList<>();
//        list.add(12);
//        list.add(54);
//        Iterator i = list.iterator();
//        assertTrue(i.hasNext());
//        assertEquals(12, i.next());
//        assertTrue(i.hasNext());
//        assertEquals(54, i.next());
//        assertFalse(i.hasNext());
//    }

//    @Test
//    public void testIteratorFromList_isFailFast(){
//        assertThrows(ConcurrentModificationException.class, ()->{
//            List<Integer> list = new customCollections.NewCustomLinkedList<>();
//            list.add(54);
//            list.add(69);
//            list.add(58);
//
//            Iterator iterator = list.iterator();
//            iterator.next();
//            list.remove(0);
//            iterator.next();
//        });
//
//    }
}
