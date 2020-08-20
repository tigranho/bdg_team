package dymanicArray;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public boolean add(E element){
        this.add(this.size, element);
        return true;
    }
}
