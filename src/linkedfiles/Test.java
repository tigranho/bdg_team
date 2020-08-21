package linkedpackage;

public class Test {
    public static void main(String[] args) throws Exception {
        LinkedImpl<Integer> linked = new LinkedImpl<>();
         linked.add(1);
         linked.add(2);
         linked.add(3);
        linked.add(1);
        linked.add(2);
        linked.add(3);
//        linked.remove();
//        linked.remove();
        linked.print();

        System.out.println("\n");
        for (int i = 0;i < linked.size - 1;i++){
            linked.remove();
        }
     //   linked.print();
        System.out.println("\n");
        for (int i = 0;i < linked.size;i++){

                System.out.print(linked.get(i) + " ");
        }
    }
}
