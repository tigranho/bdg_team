package lesson2.chapter3.streams;

import java.util.Comparator;

/**
 * @author Hrach
 */

public class DuckHelper {

    public static int compareByWeight(Duck duck1, Duck duck2) {
        return duck1.getWeight() - duck2.getWeight();
    }

    public static int compareByName(Duck duck1, Duck duck2) {
        return duck1.getName().compareTo(duck2.getName());
    }

    public static void main(String[] args) {
        Comparator<Duck> byWeight1 = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);
        Comparator<Duck> byWeight2 = DuckHelper::compareByWeight;
    }
}
