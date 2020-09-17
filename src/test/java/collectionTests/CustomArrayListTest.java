package collectionTests;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.customLists.customArrayList.CustomArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

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
            List<String> list = new CustomArrayList<String>(-1);
        });
    }

    @Test
    public void testAddElements() {
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

        assertTrue(list.size() == 4);
    }

    @Test
    public void testSetElement() {
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
    public void testRemoveElement() {
        List<String> list = new CustomArrayList<>();
        list.add(0, "Karol");
        list.add(1, "Vanessa");
        list.add(2, "Amanda");

        assertEquals("Amanda", list.remove(2));
        assertTrue(list.size() == 2);
    }

    @Test
    public void testRemoveWithEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            List<Object> list = new CustomArrayList<>();
            list.remove(0);
        });
    }

}
