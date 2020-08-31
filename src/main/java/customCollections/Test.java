package customCollections;

public class Test {
    public static void main(String[] args) {


        java.util.List<String> list2 = new NewDynamicArray<>();
        System.out.println(list2.size());

        System.out.println(list2.isEmpty());
        System.out.println(list2.add("as"));
        //     System.out.println(list2.size());
        //   System.out.println(list2.isEmpty());
        list2.add(1, "ad");
        // System.out.println(list2.get(1));
        // System.out.println(list2.remove(1));
        System.out.println(list2.size());
        System.out.println(list2);
        System.out.println(list2.set(1, "fffff"));
        System.out.println(list2);
        System.out.println(list2.size());

        NewCustomLinkedList<String> newCustomLinkedList = new NewCustomLinkedList<>();
        System.out.println(newCustomLinkedList.size());
        System.out.println(newCustomLinkedList.isEmpty());
        System.out.println(newCustomLinkedList.add("ad"));
        System.out.println(newCustomLinkedList.size());
        System.out.println(newCustomLinkedList.isEmpty());
        System.out.println(newCustomLinkedList.add("asda"));
        System.out.println(newCustomLinkedList.get(1));
        System.out.println(newCustomLinkedList.remove(1));
        System.out.println(newCustomLinkedList.remove("ad"));
        System.out.println(newCustomLinkedList.toString());
        System.out.println(newCustomLinkedList.isEmpty());
        System.out.println(newCustomLinkedList.size());



        NewCustomHashMap<String, String> customHashMap = new NewCustomHashMap<>();
//        System.out.println(customHashMap.isEmpty());
//        System.out.println(customHashMap.size());

        System.out.println(customHashMap.put("a", "dd"));
        System.out.println(customHashMap.put("v", null));
        System.out.println(customHashMap.put("v", "asass"));
        System.out.println(customHashMap.put("d", "sdf"));
        System.out.println(customHashMap.get("v"));
        System.out.println(customHashMap.put(null, "as"));
        System.out.println(customHashMap.put("adsf", null));
//        System.out.println(customHashMap.put("adsfa", null));
        System.out.println(customHashMap);

        System.out.println("");
        //  System.out.println(customHashMap.containsValue("as"));
        //System.out.println(customHashMap.containsValue("hsdghfj"));
        System.out.println(customHashMap.containsValue(null));
        System.out.println(customHashMap.get("adsfa"));
//        System.out.println(customHashMap.containsValue("a1sass"));
//        System.out.println(customHashMap.containsValue("dd"));
    }
}
