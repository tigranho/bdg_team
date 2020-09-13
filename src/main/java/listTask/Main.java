package listTask;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;

import static java.lang.invoke.MethodHandles.identity;
import static javafx.scene.input.KeyCode.T;
import static javafx.scene.input.KeyCode.V;


public class Main {


    public static void main(String[] args) {
        ArrayList<String> s = new ArrayList<>();
        CustomHashMap<String,String > customHashMaps = new CustomHashMap<>();
        HashMap<String ,String> hashMap = new HashMap<>();

        hashMap.put("Barev","Mal");
        hashMap.put("Barev10","Mal");
        hashMap.put("Barev20","Mal");

        customHashMaps.putAll(hashMap);
//        customHashMaps.put("barev","njdfjnjds");
//        customHashMaps.put("barev","njdfjnjds");
//
//        //System.out.println(customHashMaps.get("Barev"));
//        System.out.println(customHashMaps.get("barev"));

        System.out.println(customHashMaps.get("Barev"));


    }
 }



