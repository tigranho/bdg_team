package linkedpackage;

public class LinkedImpl<E> extends AbstractLinked<E> {
    Node head;
    Node tail;
    @Override
    public E get(int index) throws Exception {

        if (size==0|| index > size - 1){
            throw new Exception();
        }
        Node el;
        if (index < size / 2) {
            el = this.head;
            for (int i = 1; i <= index; i++) {
                el = el.next;
            }
        }else{
            el = this.tail;
            for (int i = 1;i <=index;i++){
                el = el.previous;
            }
        }
        return (E)el.value;
    }



    @Override
    public void add(E value) {
      Node node = new Node();
      node.value = value;
      if (this.head==null){
          this.head = node;
      }else {
          node.previous = this.tail;
          this.tail.next = node;
      }
     this.tail = node;
     size++;
    }

    @Override
    public void remove() throws Exception {

        if (this.size == 0) {
            throw new Exception("empty.");
        }

        Node el = this.head;

        if (this.size == 1) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            this.head = this.head.next;
            this.size--;
        }
        System.out.print ("close-"  + el.value + " ");

    }

    public void print(){
        Node node = head;
        for (int i = 1;i <=size;i++){
            System.out.print(node.value + " ");
            node = node.next;
        }
    }
}
