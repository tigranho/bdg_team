package task1;

/**
 *Custom implementation of ArrayList
 * @param <E>
 * @author Mary Sukiasyan
 */

public class CustomArrayList<E> {
    private static final int CAPACITY = 10;
    private int size;
    private Object data[];

    public CustomArrayList() {

        this.data = new Object[CAPACITY];
    }

    public CustomArrayList(int capacity) {
        this.data = new Object[capacity];
    }

    public boolean add(E e){
        if(size == data.length){
            sizeDoubler();
        }
        data[size++] = e;
        return true;
    }

    public void add(int index, E elem){
        if(index< 0 || index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index == size){
            add(elem);
            return;
        }
        for (int i = size; i > index ; i--) {
            this.data[i] = this.data[i-1];
        }
        this.data[index] = elem;
        this.size++;
    }

    public void sizeDoubler(){
        Object newData[] = new Object[data.length*2];
        for (int i = 0; i < data.length; i++) {
            newData[i]= data[i];
        }
        this.data = newData;
    }

    public Object get(int index){
        if(index<0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.data[index];
    }

    public Object remove(int index){
        Object removedElem = data[index];
        if(index<0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i < size()-1; i++) {
            data[i]=data[i+1];
        }
        size--;
        return removedElem;

    }

    public void set(int index, E newObj){
        if(index<0 || index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = newObj;
    }

    public void clear(){
        size = 0;
        sizeDoubler();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void display(){
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }

    public int size(){
        return this.size;
    }
}
