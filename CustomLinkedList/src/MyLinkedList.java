import java.util.LinkedList;

public class MyLinkedList {
    MyNode head;
    MyNode current;
    private  int size;


    static  public class MyNode {

        int data;
        MyNode next; 

        MyNode(int d) { data = d; }
    }

    public  void PushAt(int index,int data) {
        MyNode current = this.head;
        if(index > size || index<-1)
            return;
        for (int i = 1; i < index - 1; ++i) {
            current = current.next;
        }
        if (current != null) {
            if (index == 1) {
                MyNode item = current;
                item = new MyNode(data);
                current.next = item;
                ++this.size;
            } else {
                PushAfter(current, data);
            }

        }
    }
        private void PushAfter(MyNode node,int data)
        {
            MyNode temp = find(node);
            if (temp != null)
            {
                MyNode newitem = new MyNode(data);
                newitem.next = node.next;
                node.next = newitem;
                if (temp == current)
                {
                    current = newitem;
                }
            }
            ++size;
        }

    public  MyLinkedList pushBack( int data)
    {
        MyNode nodeToAdd= new MyNode(data);
        nodeToAdd.next = null;

        if (this.head == null) {
            this.head = nodeToAdd;
        }
        else {

            MyNode last = this.head;
            //find the end
            while (last.next != null) {
                last = last.next;
            }

            // Insertion
            last.next = nodeToAdd;
        }
        return this;
    }

    public MyNode find(MyNode node)
    {
        MyNode temp = head;
        while (temp != null)
        {
            if (temp == node)
                return temp;
            temp = temp.next;
        }
        return null;
    }


    public  void printList()
    {
        MyNode current = this.head;

        System.out.print("LinkedList contains : ");

        while (current != null) {

            System.out.print(current.data + " ");

            current = current.next;
        }
    }

    public  MyLinkedList deleteTheIndex( int index)
    {
        MyNode current = this.head,
                previos = null;

        if (index == 0 && current!= null) {
            this.head = current.next;

            return this;
        }
        int counter = 0;
        while (current!= null) {
            if (counter == index) {
                previos.next = current.next;//change the reference of previous's next
                break;
            }
            else {
                //change the reference to the next
                previos = current;
                current = current.next;
                counter++;
            }
        }
        if (current == null) {
            System.out.println(index + "No such index");
        }

        return this;
    }

    public  MyLinkedList deleteByData(int key)
    {

        MyNode current = this.head,
                previous = null;


        if (current!= null && current.data == key) {
            this.head = current.next;
            return this;
        }

        while (current!= null && current.data != key) {
            previous = current;
            current = current.next;
        }

        //if key is found
        if (current != null) {
           //change the ref
            previous.next = current.next;

        }

        if (current == null) {

            System.out.println("no such key in list");
        }

        return this;
    }
}
