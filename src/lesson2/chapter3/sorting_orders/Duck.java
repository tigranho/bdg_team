package lesson2.chapter3.sorting_orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

/**
 * @author Hrach
 */

public class Duck implements Comparable<Duck> {

    private String name;
    private int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Duck duck) {
        return name.compareTo(duck.name);
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack", 8));
        ducks.add(new Duck("Puddles", 11));
        Collections.sort(ducks);
        out.println(ducks);

        Comparator<Duck> orderByWeightAsc = Comparator.comparingInt(o -> o.weight);
        Comparator<Duck> orderByWeightDesc = (Duck d1, Duck d2) -> d2.weight - d1.weight;
        Collections.sort(ducks, orderByWeightAsc);
        out.println(ducks);
        Collections.sort(ducks, orderByWeightDesc);
        out.println(ducks);
    }
}

class Animal implements Comparable<Animal> {

    private int id;

    public Animal(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Animal animal) {
        return id - animal.id;
    }

    public static void main(String[] args) {
        Animal a1 = new Animal(5);
        Animal a2 = new Animal(7);

        out.println(a1.compareTo(a2));
        out.println(a1.compareTo(a1));
        out.println(a2.compareTo(a1));
    }
}
