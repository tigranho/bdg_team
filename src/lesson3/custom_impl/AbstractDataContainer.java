package lesson3.custom_impl;

public abstract class AbstractDataContainer {

    private int size;

    public int getAndSet(int size) {
        int oldSize = this.size;
        this.size = size;
        return oldSize;
    }

    public int size() {
        return size;
    }

    public int setAndGet(int size) {
        return this.size = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
