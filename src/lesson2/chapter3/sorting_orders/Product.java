package lesson2.chapter3.sorting_orders;

import java.util.Objects;

/**
 * @author Hrach
 */

public class Product implements Comparable<Product> {

    private int id;
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Product product) {
        return name.compareTo(product.name);
    }
}
