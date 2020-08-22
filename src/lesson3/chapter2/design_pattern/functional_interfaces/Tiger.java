package lesson3.chapter2.design_pattern.functional_interfaces;

import lesson3.chapter2.design_pattern.interfaces.Animal;

/**
 * @author Hrach
 */

public class Tiger implements Sprint {
    @Override
    public void sprint(Animal animal) {
        System.out.println("Animal is sprinting fast! "+animal.toString());
    }
}
