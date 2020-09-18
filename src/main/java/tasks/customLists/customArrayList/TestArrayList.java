package tasks.customLists.customArrayList;

/**
 * @author Tatevik Mirzoyan
 */
public class TestArrayList {
    public static void main(String[] args) {
        CustomArrayList<String> stringList = new CustomArrayList<>();
        stringList.add("Sunday");
        stringList.add("Monday");
        stringList.add("Tuesday");
       // stringList.add("Not day");
      //  stringList.add("Sunday");
        stringList.add("Thursday");
        stringList.add("Friday");
        stringList.add("Saturday");
       // stringList.remove(4);
       // stringList.remove("Not day");
        stringList.add(3,"Wednesday");
        System.out.println(stringList.toString());


    }
}
