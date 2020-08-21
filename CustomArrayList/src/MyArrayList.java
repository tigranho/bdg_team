
import jdk.internal.util.ArraysSupport;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final Object[] EMPTY_INNERARR = {};

    private Object[] innerArr;
    private int size;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.innerArr = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.innerArr = EMPTY_INNERARR;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }


    public MyArrayList() {
        this.innerArr = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return !(indexOf(o)==-1);
    }

    private void add(E e, Object[] innerArr, int s) {
        if (s == innerArr.length)
            innerArr = grow();
        innerArr[s] = e;
        size = s + 1;
    }

    @Override
    public boolean add(E e) {
        add(e, innerArr, size);
        return true;

    }
    private Object[] grow(int minCapacity) {
        int oldCapacity = innerArr.length;
        if (oldCapacity > 0) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);//if minCapacity is less than prefered ,prefered is used,otherwise minCapacity
            return innerArr = Arrays.copyOf(innerArr, newCapacity);
        } else {
            return innerArr = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }
    private Object[] grow() {
                if(innerArr.length==0) {
                    innerArr=new Object[DEFAULT_CAPACITY];
                }
                else
                    innerArr=Arrays.copyOf(innerArr, innerArr.length*2);
            return innerArr;
    }

    @Override
    public boolean remove(Object o) {
        if(!MyArrayList.this.contains(o))
            throw new NoSuchElementException();
         Object[] arr = innerArr;
         int size = this.size;
         if(o==null)
         {
             for (int i=0; i < size; i++) {
                 if (innerArr[i] == null) {
                     fastRemove(arr, i);
                     return true;
                 }
             }
         }
         else{
             for (int i=0; i < size; i++) {
                 if (o.equals(innerArr[i])) {
                     fastRemove(innerArr,i);
                     return true;
                 }
             }


         }
         return false;
    }
    private void fastRemove(Object[] arr, int i) {
        int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(arr, i + 1, arr, i, newSize - i);

        arr[size = newSize] = null;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] a = c.toArray();
        for(int i=0;i<a.length;++i)
        {
           if(!this.contains(a[i]))
               return false;
        }
        return true;

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();

        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] innerArr;
         int s;
        innerArr = this.innerArr;
        s = size;
        if (numNew >this.innerArr.length-size)
        {
            innerArr = grow(s + numNew);
        }
            System.arraycopy(a, 0, innerArr, s, numNew);
            size = s + numNew;
            return true;


    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        Object[] a = c.toArray();

        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] innerArr=this.innerArr;
         int s=size;
        if (numNew > innerArr.length - s )
            innerArr = grow(s + numNew);

        int numMoved = s - index; //how many items are after index
        if (numMoved > 0)
            System.arraycopy(innerArr, index,
                    innerArr, index + numNew,
                    numMoved);
        System.arraycopy(a, 0, innerArr, index, numNew);
        size = s + numNew;
        return true;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(c==null)
        {
             throw new NullPointerException();
        }
        Object[] arr=c.toArray();
        Object[] innerArr=this.innerArr;
        boolean isChanged=false;
        for(int i=0;i<arr.length;++i)
        {
           for(int j=0;j< innerArr.length;++j)
           {
               if(innerArr[j]==arr[i])
               {   remove(j);
               isChanged=true;
               }

           }
        }
        return isChanged;

    }


    @Override
    public boolean retainAll(Collection<?> c) {
        if(c==null)
        {
            throw new NullPointerException();
        }
        Object[] arr=c.toArray();
        Object[] innerArr=this.innerArr;
        boolean isChanged=false;
        boolean contains=false;



            for(int j=0;j< innerArr.length;++j)
            {
                for(int i=0;i<arr.length;++i) {
                    if (innerArr[j] == arr[i]) {
                        contains=true;
                        break;
                    }
                }
                if(!contains)
                {
                    remove(j);
                    isChanged=true;
                }

            }

        return isChanged;


    }

    @Override
    public void clear() {
        Object[] arr=innerArr;
        for (int i=0;i<size;++i)
        {
            arr[i]=null;
        }
        size=0;

    }
    @Override
    public Iterator<E> iterator() {
        return new MyArrayList.Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(innerArr, size);
    }


    //cant understand the use of this method
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(innerArr, size, a.getClass());
        System.arraycopy(innerArr, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public E get(int index) {
        if(indexOf(index)>=0 && indexOf(index)< innerArr.length)
        {
            return (E) innerArr[index];
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(index>=0 && index< innerArr.length)
        {
            E val=(E)innerArr[index];
            innerArr[index]=element;
            return val;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();

         int s;
        Object[] innerArr;

        s = size;
        innerArr=this.innerArr;
        if(size== this.innerArr.length)
        {
            innerArr=grow();
        }

        System.arraycopy(innerArr, index,
                innerArr, index + 1,
                s - index);
        innerArr[index] = element;
        size = s + 1;

    }

    @Override
    public E remove(int index) {
        if(index>0 ||index>size)
            throw new NoSuchElementException();
         Object[] arr = innerArr;

         E oldValue = (E) arr[index];
        fastRemove(arr, index);

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        Object[] arr = innerArr;
            if (o == null) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        return i;
                    }
                }
            } else {
                for (int i = 0; i < arr.length; i++) {
                    if (o.equals(arr[i])) {
                        return i;
                    }
                }
            }
            return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Object[] arr = innerArr;
        if (o == null) {
            for (int i = arr.length-1; i < 0; i--) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = arr.length-1; i <0; i--) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;

    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        //int expectedModCount = modCount;  i dont understand what is modCount for

        // prevent creating a synthetic constructor
        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }


        public E next() {

            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] innerArr = MyArrayList.this.innerArr;
            if (i >= innerArr.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            lastRet=i;
            return (E) innerArr[i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();


            try {
                MyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1; //why is it needed to set lastRet to -1
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }


    private class ListItr extends Itr implements ListIterator<E>{

        ListItr(int index) {
            super();
            cursor = index;
        }
        @Override
        public boolean hasPrevious() {
            return cursor!=0;
        }

        @Override
        public E previous() {
            int prevInd=cursor-1;
            if(prevInd<0)
            {
                throw new NoSuchElementException();
            }
            else
            {
                Object[] innerArr=MyArrayList.this.innerArr;
                if(prevInd>=innerArr.length)
                    throw new NoSuchElementException();
                cursor = prevInd;
                lastRet=prevInd;
                return (E) innerArr[prevInd];
            }

        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor-1;
        }

        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();


            try {
                MyArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }


        }

        @Override
        public void add(E e) {
            try {
                int i = cursor;
                MyArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;

            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }
    }
        @Override
    public ListIterator<E> listIterator() {
            return new MyArrayList.ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
         return new MyArrayList.ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
     return new ArrayList<>();
    }
}
