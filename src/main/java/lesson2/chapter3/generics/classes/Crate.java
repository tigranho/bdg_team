package lesson2.chapter3.generics.classes;

public class Crate<T> {
    private T content;

    public T emptyCrate() {
        return content;
    }

    public void packCrate(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        Crate<Elephant> crateForElephant = new Crate<>();
        Elephant elephant = new Elephant();
        crateForElephant.packCrate(elephant);
        elephant = crateForElephant.emptyCrate();

        Crate<Zebra> crateForZebra = new Crate<>();
        Crate<Robot> crateForRobot = new Crate<>();

    }
}