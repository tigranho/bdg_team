package customCollections;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class NewDynamicArray<E> extends AbstractList<E> implements List<E> , Iterator<E> {
    private int size;
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private Object[] array;
    private static final int DEFAULT_CAPACITY = 10;

    public NewDynamicArray() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public NewDynamicArray(int length) {
        if(length < 0){
            throw new IllegalArgumentException("Array's capacity can't be negative"+ "Illegal capacity " + length);
        }
        this.array = new Object[length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        checkAndIncrease();
        this.array[size++] = element;
        ++modCount;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkAndIncrease();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("check array size before adding the following index");
        }
        if (index == size) {
            add(element);
            return;
        }
        for (int i = this.size; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
        this.array[index] = element;
        this.size++;
        ++modCount;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("can't access array out of its bound");
        }
        return (E) this.array[index];
    }

    @Override
    public E set(int index, E element) {
        E previous;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("can't access array out of its bound");
        }
        previous = (E) this.array[index];
        this.array[index] = element;
        return previous;
    }

    @Override
    public E remove(int index) {
        E removingElement;
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("can't remove array's element by index out of its bound");
        }
        removingElement = (E) this.array[index];
        for (int i = index; i < size - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.size--;
        ++modCount;
        return removingElement;
    }

    @Override
    public boolean remove(Object o) {
        if(o == null){
            for(int i = 0; i < size; i++){
                if(array[i] == null){
                    remove(i);
                    return true;
                }
            }
        }else{
            for(int i = 0; i < size; i++){
                if(o.equals(array[i])){
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        if(o == null){
            for(int i = 0; i < size; i++){
                if(array[i] == null){
                    return i;
                }
            }
        }else {
            for(int i = 0; i < size; i++){
                if(o.equals(array[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(o == null){
            for(int i = size - 1; i >= 0; i--){
                if(array[i] == null){
                    return i;
                }
            }
        }else {
            for(int i = size - 1; i >= 0; i--){
                if(o.equals(array[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        if(indexOf(o) >= 0){
            return true;
        }
        return false;
    }



    //    @Override
//    public boolean addAll(dymanicArray.List<? extends E> list) {
//        if (list == null) {
//            throw new NullPointerException("can't add null to existing list");
//        }
//        checkAndIncrease(list.getSize());
//        for (int i = 0; i < list.getSize(); i++) {
//            this.array[size++] = list.get(i);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean addAll(dymanicArray.List<? extends E> list, int startIndex, int count) {
//        int elCount = list.getSize() - startIndex > count ? count : list.getSize() - startIndex;
//        checkAndIncrease(elCount);
//        for (int i = startIndex; i < startIndex + elCount; i++) {
//            this.array[size++] = list.get(i);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean insertAll(dymanicArray.List<? extends E> list, int position) {
//        if (list == null) {
//            throw new NullPointerException("inserted list can't be null");
//        }
//        if (position < 0 || position > size) {
//            throw new IndexOutOfBoundsException("position can't be out of array bound");
//        }
//        if (position == size) {
//            addAll(list);
//        }
//        int elCount = list.getSize();
//        checkAndIncrease(elCount);
//        for (int i = this.size + elCount - 1; i >= position + elCount; i--) {
//            this.array[i] = this.array[i - elCount];
//        }
//        System.out.println(this.array);
//
//        for (int i = 0; i < list.getSize(); i++) {
//            //add(position++, list.get(i));
//            this.array[position++] = list.get(i);
//        }
//        this.size += list.getSize();
//        return true;
//    }


    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super E> consumer) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }

    private void checkAndIncrease() {
        if (this.size >= this.array.length) {
            Object[] newArray = new Object[this.array.length + this.array.length / 2];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    private void checkAndIncrease(int arrayLength) {
        if (this.size + arrayLength > this.array.length) {
            Object[] newArray = new Object[this.size + arrayLength + (this.size + arrayLength) / 2];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < this.size; i++) {
            s.append(this.array[i]);
            if (i < this.size - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}
