package lesson2.chapter3.sorting_orders;

import java.util.Comparator;

/**
 * @author Hrach
 */

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book book, Book t1) {
        return book.compareTo(t1);
    }

    @Override
    public Comparator<Book> reversed() {
        return Comparator.reverseOrder();
    }
}
