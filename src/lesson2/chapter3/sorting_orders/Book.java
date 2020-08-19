package lesson2.chapter3.sorting_orders;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Hrach
 */

public class Book implements Comparable<Book> {

    private String title;
    private String author;
    private int pageCount;

    public Book(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public int compareTo(Book book) {
        int result = title.compareTo(book.title);
        if (result == 0) {
            result = pageCount - book.pageCount;
            if (result == 0) {

                result = author.compareTo(book.author);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pageCount);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("pageCount=" + pageCount)
                .toString();
    }
}
