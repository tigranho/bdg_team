package customLists;

/**
 * @author Tatevik Mirzoyan
 */
public class TestArrayList {
    public static void main(String[] args) {
        MyCustomArrayList<String> stringList = new MyCustomArrayList<>();
        stringList.add("Sunday");
        stringList.add("Monday");
        stringList.add("Tuesday");
        stringList.add("xxx");
        stringList.add("Not day");
        stringList.add("Sunday");
        stringList.add("Thursday");
        stringList.add("Friday");
        stringList.add("Saturday");
        stringList.set(3, "Wednesday");
        stringList.remove(5);
        stringList.remove("Not day");
        // stringList.clear();
        if (stringList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("List's size is " + stringList.size());
            System.out.println(stringList.toString());
        }

        MyCustomArrayList<Integer> integerList = new MyCustomArrayList<>();
        integerList.add(12);
        integerList.add(54);
        integerList.add(36);
        integerList.add(18);
        integerList.set(1, 45);
        integerList.remove(2);
        System.out.println("List's size is " + integerList.size());
        System.out.println(integerList.toString());


    }
}
