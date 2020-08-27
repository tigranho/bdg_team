package lesson2.chapter3.sorting_orders;

import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class MySortingOrder {
    public static void main(String[] args) {
        var book1 = new Book("Giqor@", "H. Tumanyan", 345);
        var book2 = new Book("Xent@", "Raffi", 124);
        var book3 = new Book("Xent@", "Dostoevski", 201);
        var book4 = new Book("Yo yertas@", "H. Senkeevich", 432);
        var books = Stream.of(book1, book2, book3, book4).collect(TreeSet::new, TreeSet:: add, TreeSet::addAll);
        out.println(books);
        out.println();

        var bookComparator = new BookComparator()
                .thenComparing(Book::getAuthor);
        var queue = new  PriorityQueue<>(bookComparator);
        queue.add(book1);
        queue.add(book2);
        queue.add(book3);
        queue.add(book4);
        out.println(queue);
        out.println();

        var reversedQueue = new PriorityQueue<>(bookComparator.reversed());
        reversedQueue.addAll(queue);
        reversedQueue.forEach(el -> out.print(el + ","));
    }
}
