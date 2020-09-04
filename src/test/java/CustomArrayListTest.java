
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1.CustomArrayList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {
    @Test
    public void testListInit(){
        CustomArrayList<Object> list =  new CustomArrayList<>();
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    @Test
    public void givenNonEmptyList_whenIsEmpty_thenFalseIsReturned(){
        CustomArrayList<Object> list = new CustomArrayList<>();
        list.add(null);
        assertFalse(list.isEmpty());
    }

    @Test
    public void  givenEmptyList_whenElementIsAdded_thenGetReturnsThatElement(){
        CustomArrayList<Object> list = new CustomArrayList<>();
        boolean succeeded = list.add(null);
        assertTrue(succeeded);
    }

    @Test
    public void testInvalidCapacity(){
        Assertions.assertThrows(NegativeArraySizeException.class, () -> {
            CustomArrayList<String> list = new CustomArrayList<>(-1);
        });
    }

    @Test
    public void testAddElements(){
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(0, "bag");
        list.add(1, "shoes");
        list.add(2, "wallet");

        assertEquals("bag", list.get(0));
        assertEquals("shoes", list.get(1));
        assertEquals("wallet", list.get(2));

        list.add(2, "brushes");

        assertEquals("bag", list.get(0));
        assertEquals("shoes", list.get(1));
        assertEquals("brushes", list.get(2));
        assertEquals("wallet", list.get(3));

        assertTrue(list.size() == 4);
    }

}
