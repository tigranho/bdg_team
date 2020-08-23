package customLinkedList;

public class CustomLinkedList<E> implements List<E> {
    private int size;
    private CustomLinkedList.Node<E> head;
    private CustomLinkedList.Node<E> tail;
    private static final long serialVersionUID = 876323262645176374L;

    public CustomLinkedList() {
    }

    public CustomLinkedList(List<? extends E> list) {
        for (int i = 0; i < list.getSize(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E element) {
        CustomLinkedList.Node newElement = new CustomLinkedList.Node(element);
        if (this.head == null) {
            this.head = newElement;
        } else {
            newElement.previous = this.tail;
            this.tail.next = newElement;
        }
        this.tail = newElement;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Given index out of array's bound");
        }
        if (index == size) {
            this.add(element);
            return;
        }
        CustomLinkedList.Node newElement = new CustomLinkedList.Node(element);
        CustomLinkedList.Node oldElement;
        oldElement = this.head;
        for (int i = 0; i <= index; i++) {
            oldElement = this.head.next;
        }
        newElement.previous = oldElement.previous;
        newElement.next = oldElement;
        if (oldElement == this.head) {
            this.head = newElement;
        } else {
            oldElement.previous.next = newElement;
        }
        oldElement.previous = newElement;
        size++;
    }

    @Override
    public E get(int index) {
        checkIndexRange(index);
        return (E) getNodeByIndex(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkIndexRange(index);
        CustomLinkedList.Node replacingElement = getNodeByIndex(index);
        replacingElement.item = element;
        return (E) replacingElement.item;
    }

    @Override
    public E remove(int index) {
        checkIndexRange(index);
        CustomLinkedList.Node removingElement = getNodeByIndex(index);
        if (removingElement == this.head) {
            this.head = removingElement.next;
            removingElement.next.previous = null;
        } else if (removingElement == this.tail) {
            this.tail = removingElement.previous;
            removingElement.previous.next = null;
        } else {
            removingElement.previous.next = removingElement.next;
            removingElement.next.previous = removingElement.previous;
        }
        size--;
        return (E) removingElement.item;
    }

    @Override
    public boolean remove(Object object) {
        CustomLinkedList.Node removingElement = new CustomLinkedList.Node((E) object);
        CustomLinkedList.Node el = this.head;
        int index = 0;
        while (el != null) {
            if (el.item == removingElement.item) {
                remove(index);
                return true;
            }
            el = el.next;
            index++;
        }
        return false;
    }

    @Override
    public boolean addAll(List<? extends E> list) {
        if (list == null) {
            throw new NullPointerException("Added list can not be null");
        }
        for (int i = 0; i < list.getSize(); i++) {
            add(list.get(i));
        }
        return true;
    }

    @Override
    public boolean addAll(int position, List<? extends E> list) {
        if (position < 0 || position > size) {
            throw new ArrayIndexOutOfBoundsException("Given index out of array's bound");
        }
        for (int i = 0; i < list.getSize(); i++) {
            add(position++, list.get(i));
        }
        return true;
    }

    private CustomLinkedList.Node<E> getNodeByIndex(int index) {
        checkIndexRange(index);
        CustomLinkedList.Node element;
        if (index == 0) {
            element = this.head;
        } else if (index == size - 1) {
            element = this.tail;
        } else if (index <= size / 2) {
            element = this.head;
            for (int i = 0; i <= index; i++) {
                element = element.next;
            }
        } else {
            element = this.tail;
            for (int i = size - 1; i > index; i--) {
                element = element.previous;
            }
        }
        return element;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Given index out of array's bound");
        }
    }

    private static class Node<E> {
        E item;
        CustomLinkedList.Node<E> previous;
        CustomLinkedList.Node<E> next;

        Node(Node<E> var1, E item, Node<E> var2) {
            this.previous = var1;
            this.item = item;
            this.next = var2;
        }

        Node(E item) {
            this.item = item;
        }
    }
}
