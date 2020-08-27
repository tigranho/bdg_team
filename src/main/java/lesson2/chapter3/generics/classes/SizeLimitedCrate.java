package lesson2.chapter3.generics.classes;

public class SizeLimitedCrate<T, U extends Number> {
    private T content;
    private U sizeLimit;

    public SizeLimitedCrate(T content, U sizeLimit) {
        this.content = content;
        this.sizeLimit = sizeLimit;
    }

    public void addToCrate(T content) {
        this.content = content;
    }

    public U getSizeLimit() {
        return sizeLimit;
    }

    public static void main(String[] args) {
        Elephant jumbo = new Elephant();
        int numPounds = 15_000;
        SizeLimitedCrate<Elephant, Integer> c1 = new SizeLimitedCrate<>(jumbo, numPounds);
    }
}
