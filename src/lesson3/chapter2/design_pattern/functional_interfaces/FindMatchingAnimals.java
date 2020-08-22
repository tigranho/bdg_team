package lesson3.chapter2.design_pattern.functional_interfaces;

/**
 * @author Hrach
 */

public class FindMatchingAnimals {

    private static void print(Animal animal, CheckTrait trait) {
        if (trait.test(animal)) System.out.println(animal);
    }

    public static void main(String[] args) {
        print(new Animal("fish", false, true), Animal::isCanHop);
        print(new Animal("kangaroo", true, false), Animal::isCanSwim);
    }
}
