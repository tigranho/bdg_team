package linkedpackage;

public abstract class AbstractLinked<E> {
      int size;
     Node node = new Node();

    public abstract E get(int index);
    public abstract void add(E value);
    public abstract void remove(int index);



}
