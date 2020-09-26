package lesson13.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFromJsonToObj {
    public static void main(String[] args) {
        String json = " {\"brand\": \"Rover\", \"doors\": 5 , \"isBroken\":true} ";
       Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Car car = gson.fromJson(json, Car.class);


        System.out.println(car); //Car[brand='Rover', doors=0, isBroken=true]
    }
}
