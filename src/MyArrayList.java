import java.util.*;

/**
 * @param <E> access all reference types
 * @author Hrach M
 */
public abstract class MyArrayList<E> implements List<E> {

    private int size = 0;
    private static final int capasitu = 8;

    private Object elements[];


    public MyArrayList() {
        elements = new Object[capasitu];
    }
    public boolean add(E e) {
        if (size == elements.length) {
            ArrSize();
        }
        elements[size++] = e;
        return false;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (E) elements[i];
    }


    @SuppressWarnings("unchecked")
    public E remove(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        Object item = elements[i];
        int numE = elements.length - ( i + 1 ) ;
        System.arraycopy( elements, i + 1, elements, i, numE ) ;
        size--;
        return (E) item;
    }

    public int size() {
        return size;
    }




    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size ;i++) {
            sb.append(elements[i].toString());
            if(i<size-1){
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    private void ArrSize() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

        public static void main(String[] args)
        {
            MyArrayList<Integer> list = new MyArrayList<>() {
                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<Integer> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends Integer> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends Integer> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public Integer set(int index, Integer element) {
                    return null;
                }

                @Override
                public void add(int index, Integer element) {

                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Integer> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<Integer> listIterator(int index) {
                    return null;
                }

                @Override
                public List<Integer> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };

            list.add(2);
            list.add(1);
            list.add(5);
            list.add(4);
            list.add(3);
            System.out.println(list);

            list.remove(2);
            System.out.println(list);

            System.out.println( list.get(0) );
            System.out.println( list.get(1) );

            System.out.println(list.size());

    }
}