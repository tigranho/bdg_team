package task1;

public class Tester {
    public static void main(String[] args) {

        CustomArrayList<String> pinkItems = new CustomArrayList<String>();
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

        CustomLinkedList<String> n = new CustomLinkedList<>();
        n.insert("a");
        n.insert("b");
        n.insert("c");
        n.insert("d");
        n.insertFirst("e");
        n.removeFirst();
        n.removeAt(0);

        n.printList();

        CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();
        hashMap.put("a", 1);
        hashMap.put("b", 2);
        hashMap.display();
        System.out.println(hashMap.get("a"));
        hashMap.remove("b");
        hashMap.display();
    }
}
