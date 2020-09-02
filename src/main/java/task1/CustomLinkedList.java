package task1;

public class CustomLinkedList<E> {
    Node head;

    class Node{
        E data;
        Node next;
    }

    public void insert(E data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        if(head == null){
            head = node;
        }
        else{
            Node n = head;
            while(n.next != null) {
                n = n.next;
            }
                n.next = node;
        }
    }

    public void insertFirst(E data){
        Node node = new Node();
        node.data = data;
        node.next = head;
        head = node;
    }

    public void removeFirst(){
        head = head.next;
    }

    public void removeAt(int index){
        if(index == 0){
            head = head.next;
        }
        else {
            Node node = head;
            Node node1 = null;
            for (int i = 0; i < index-1; i++) {
                node = node.next;
            }
            node1 = node.next;
            node.next = node1.next;
        }
    }

    public void removeLast(){

    }

    public void printList(){
        Node current = head;
        while(current.next != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println(current.data);
    }
}