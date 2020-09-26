package lesson13.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFromObjToJson {
    public static void main(String[] args) {
        Car car = new Car("Rover", 5, false);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(car);
        System.out.println(json);//{"brand": "Rover", "doors": 5 }
    }
}
