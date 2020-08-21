package linkedpackage;

public class LinkedImpl<E> extends AbstractLinked<E> {
    Node head;
    Node tail;
    @Override
    public E get(int index) {
        Node el = new Node();
        if (index < size / 2) {
            el = head;
            for (int i = 1; i <= index; i++) {
                el = el.next;
            }
        }else{
            el = el.previous;
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
      if (value==null){
          this.head = node;
      }
      node.previous = this.tail;
      node.previous.next = this.tail.previous;
      this.node = tail;
    }

    @Override
    public void remove(int index) {
       Node el = head;
       if (el==null){
           return;
       }
       if (index == 0){
           el = el.next;
       }

      if (el!=null){
          for (int i = 0;i < size - 1;i++){
              el = el.next;
          }
          Node next = el.next.next.next;
      }
    }
}
