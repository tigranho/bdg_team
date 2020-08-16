package lesson2.chapter3.generics.interfaces;

public class ShippableAbstractCrate<U> implements Shippable<U> {
    @Override
    public void ship(U u) {

    }
}

class ShippableCrate implements Shippable {

    @Override
    public void ship(Object o) {

    }
}