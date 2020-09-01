package Customs;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    @Test
    public void testListInit() {
        List<Object> list = new CustomLinkedList<>();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
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

        list.add(1, "Mariana");

        assertEquals("Karol", list.get(0));
        assertEquals("Mariana", list.get(1));
        assertEquals("Vanessa", list.get(2));
        assertEquals("Amanda", list.get(3));

        assertTrue(list.size() == 4);
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
        List<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Amanda", list.remove(2));
        assertTrue(list.size() == 2);
    }

    @Test
    public void testRemoveWithEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Object> list = new CustomLinkedList<>();
            list.remove(0);
        });
    }

    @Test
    public void testIndexOf() {
        List<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, null);
        list.add(2, "Karol");

        assertTrue(list.indexOf("Karol") == 0);
        assertTrue(list.indexOf(null) == 1);
        assertTrue(list.lastIndexOf("Karol") == 2);
    }

    @Test
    public void testPoll() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");

        assertEquals(list.poll(), "Karol");
        assertTrue(list.size() == 1);
    }

    @Test
    public void testPeek() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");

        assertEquals(list.peek(), "Karol");
        assertTrue(list.size() == 2);
    }

    @Test
    public void testPushAndPop() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.push("Vanessa");
        list.push("Karol");

        assertEquals(list.pop(), "Karol");
        assertTrue(list.size() == 1);
    }

    @Test
    public void testPopWithEmptyList() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            CustomLinkedList<Object> list = new CustomLinkedList<>();
            list.pop();
        });
    }

    @Test
    public void testPollWithEmptyList() {
        CustomLinkedList<Object> list = new CustomLinkedList<>();

        assertEquals(list.poll(), null);
    }
}