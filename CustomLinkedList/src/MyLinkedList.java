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

    public  void PushAt(MyLinkedList linkedList,int index,int data) {
        MyNode current = linkedList.head;
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
                ++linkedList.size;
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

    public static MyLinkedList pushBack(MyLinkedList linkedList, int data)
    {
        MyNode nodeToAdd= new MyNode(data);
        nodeToAdd.next = null;

        if (linkedList.head == null) {
            linkedList.head = nodeToAdd;
        }
        else {

            MyNode last = linkedList.head;
            //find the end
            while (last.next != null) {
                last = last.next;
            }

            // Insertion
            last.next = nodeToAdd;
        }
        return linkedList;
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


    public static void printList(MyLinkedList list)
    {
        MyNode current = list.head;

        System.out.print("LinkedList contains : ");

        while (current != null) {

            System.out.print(current.data + " ");

            current = current.next;
        }
    }

    public static MyLinkedList deleteTheIndex(MyLinkedList linkedList, int index)
    {
        MyNode current = linkedList.head,
                previos = null;

        if (index == 0 && current!= null) {
            linkedList.head = current.next;

            return linkedList;
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

        return linkedList;
    }

    public static MyLinkedList deleteByData(MyLinkedList list, int key)
    {

        MyNode current = list.head,
                previous = null;


        if (current!= null && current.data == key) {
            list.head = current.next;
            return list;
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

        return list;
    }
}
