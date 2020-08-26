package customLists;

/**
 * @author Tatevik Mirzoyan
 */
public class MyCustomLinkedList {
    private int size = 0;
    private Node last;

    /**
     * Default constructor of an empty list.
     */
    public MyCustomLinkedList() {
    }


    //???
    public void remove(Object o) {
        if (last == null) {
            System.out.println("Empty list");
        } else {
            Node removedNode;
            Node currentNode = last;
            while (currentNode.next.next != null) {
                if (currentNode.next.item == o) {
                    currentNode.next = currentNode.next.next;
                }
                currentNode = currentNode.next;
            }
        }
        size--;
    }

    //???
    public void remove(int index) {
        checkIndex(index);
        Node removedNode;
        Node currentNode = last;
        while (currentNode.next != null) {
            removedNode = node(index);
            remove(removedNode.item);
            currentNode = currentNode.next;
        }
    }






    // OK

    public void add(Object o) {
        Node newNode = new Node(o);
        if (last == null) {
            last = newNode;
        } else {
            Node currentNode = last;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
    }

    public Object get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    public Object set(int index, Object o) {
        checkIndex(index);
        Node oldNode = last;
        Node newNode = new Node(o);
        Node currentNode = last;
        while (currentNode.next != null) {
            oldNode = node(index);
            node(index).item = newNode.item;
            currentNode = currentNode.next;
        }
        return oldNode.item;
    }


    /**
     * Checks if the index is out of bounds or not.
     *
     * @param index the index that has to be checked
     * @throws IndexOutOfBoundsException .
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("The index is out of bounds");
        }
    }

    private Node node(int index) {
        Node indexNode = last;
        for (int i = 0; i < index; i++)
            indexNode = indexNode.next;
        return indexNode;
    }


    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    public void clear() {
        size = 0;

    }


    @Override
    public String toString() {
        if (last == null) {
            System.out.println("Empty list");
        } else {
            System.out.println("This is my list");
            Node currentNode = last;
            while (currentNode.next != null) {
                System.out.print(currentNode.item + " ");
                currentNode = currentNode.next;
            }
            System.out.print(currentNode.item);
        }
        return "";
    }

    private static class Node {
        Object item;
        Node next;

        public Node(Object item) {
            this.item = item;
        }

        public Node(Object element, Node next) {
            this.item = element;
            this.next = next;

        }
    }

}
