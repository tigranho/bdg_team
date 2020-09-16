package model;

import org.w3c.dom.ls.LSOutput;

public class Product {
    private String maker;
    private String model;
    private String type;


    @Override
    public String toString() {
        return "Product{" +
                "maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

