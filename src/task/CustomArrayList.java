package task;

import java.util.*;

public class CustomArrayList<E> implements List<E> {
    private int capacity=10;
    private int size=0;
    private Object[] array;

    public CustomArrayList() {
        this.array =  new Object[capacity];
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    public CustomArrayList(Collection<? extends E> c) {
        capacity=c.size();
        size=c.size();
        array = c.toArray();
        if ((size = array.length) != 0) {
            // defend against c.toArray (incorrectly) not returning Object[]
            // (see e.g. https://bugs.openjdk.java.net/browse/JDK-6260652)
            if (array.getClass() != Object[].class)
                array = Arrays.copyOf(array, size, Object[].class);
        } else {
            // replace with empty array.
            this.array = new Object[0];
        }
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        int temp=indexOf(o);
        if (temp==-1){
            return false;
        }
        return true;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array,size);
    }


    public boolean add(E e) {
        size++;
        if(size == capacity){
            capacity=3/2*capacity+1;
            this.array = coppyArray(capacity);
            array[size-1]=e;
            return true;
        }
        array[size-1]=e;
        return true;
    }



    @Override
    public boolean remove(Object o) {
        int temp=indexOf(o);
        if (temp==-1) return false;
        deleteElement(temp);
        size--;
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean flag = false;
        Iterator<E> iter = c.iterator();
        while(iter.hasNext()){
            flag= add(iter.next()) || flag;
            flag=true;
        };
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        boolean flag = false;
        int i = index;
        Iterator<E> iter = c.iterator();
        while(iter.hasNext()){
            add(i,iter.next());
            i++;
            flag=true;
        };
        return flag;
    }

    @Override
    public void clear() {
        this.size=0;
    }

    @Override
    public E get(int index) {
        return (E) array[index];
    }

    @Override
    public E set(int index, E element) {
        E e=null;
        if ((index >= 0) && (index<size) && (size>=0)){
            e = (E) array[index];
            array[index]=element;

        }
        return e;
    }

    @Override
    public void add(int index, Object element) {
        if(size == capacity){
            capacity=3/2*capacity+1;
            this.array = coppyArray(capacity);
        }
        size++;
        System.arraycopy(array,index,array,index+1,size-index -1);
        array[index]=element;

    }

    @Override
    public E remove(int index) {
        if (size>0) {
            E e = (E) array[index];
            deleteElement(index);
            size--;
            return e;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0; i < this.size ; i++) {
                if (array[i] == null){
                    return i;
                }
            }
        }
        for (int i = 0; i < this.size ; i++) {
            if(o.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for (int i = size-1; i >= 0 ; i--) {
                if (array[i] == null){
                    return i;
                }
            }
        }
        for (int i = size-1; i >= 0 ; i--) {
            if(o.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if ((toIndex>fromIndex) && (fromIndex>=0) && (toIndex<=size)){
            List<E> list = new ArrayList<>();
            for (int i = fromIndex; i < toIndex ; i++) {
                list.add((E)array[i]);
            }
        }
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean flag=false;
        for (int i = 0; i < size ; i++) {
            if (!c.contains(array[i])){
                flag=true;
                remove(array[i]);
            }
        }
        return flag;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (size>0) {
            boolean flag = false;
            Iterator<E> iter = c.iterator();
            while (iter.hasNext()) {
                flag = remove(iter.next()) || flag;

            }
            return flag;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        CustomArrayList<E> arr =new CustomArrayList<>(this);
        Iterator<E> iter = c.iterator();
        while(iter.hasNext()){
            E e = iter.next();
            System.out.println(arr.indexOf(e)+"sig");
            if(arr.indexOf(e)!=-1){
                System.out.println(remove(e));
                for (int i = 0; i < size(); i++) {
                    System.out.println(array[i]);
                }
            }else{
                return false;
            }
        }
        return true;
    }

    @Override
    public <E> E[] toArray(E[] e) {
        if (e.length < size)
            return (E[]) Arrays.copyOf(array, size, e.getClass());
        System.arraycopy(array, 0, e, 0, size);
        if (e.length > size)
            e[size] = null;
        return e;
    }

    private Object[] coppyArray(int a){
        Object[] o = new Object[capacity];
        for (int i = 0; i < array.length; i++) {
            o[i] = array[i];
        }
        return o;
    }

    private void deleteElement(int n){

        for (int i = n; i <size-1 ; i++) {
            array[i]=array[i+1];
        }
        array[size-1]=null;
    }

    private  void copy_Array(Collection<? extends E> c) {
        int i=0;
        Iterator<? extends E> iterator = c.iterator();
        while ( iterator.hasNext()){
            array[i]=iterator.next();
            i++;
        }

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(array[i].toString()).append(", ");
        }
        s.delete(s.length()-2,s.length());
        return s.toString();
    }
}
