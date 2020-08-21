package task1;

public class Tester {
    public static void main(String[] args) {

        CustomArrayList<String> pinkItems = new CustomArrayList<>();
        pinkItems.add("phone");
        pinkItems.add("bag");
        pinkItems.add("wallet");
        pinkItems.add("car");
        pinkItems.add("shoes");
        pinkItems.add("PJs");
        pinkItems.add("highlighter");

        System.out.println("Element removed: " + pinkItems.remove(1));

        System.out.println("Index getter: " + pinkItems.get(1));

        System.out.println(pinkItems.isEmpty());

        pinkItems.set(4, "watch");

        pinkItems.display();
        System.out.println(pinkItems.size());

        pinkItems.clear();

        CustomLinkedList n = new CustomLinkedList();
        n.insert(1);
        n.insert(2);
        n.insert(1);
        n.insert(2);
        n.insertFirst(3);
        n.removeFirst();
        n.removeAt(0);

        n.printList();


    }
}
