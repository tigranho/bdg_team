package lesson13.gson;

import com.google.gson.annotations.Expose;

import java.util.StringJoiner;

public class Car {
    @Expose(serialize = true, deserialize = true)
    private String brand;
    @Expose(serialize = true, deserialize = false)
    private int doors;
    @Expose(serialize = false, deserialize = true)
    private boolean isBroken;
    public static final byte WHEEL_COUNT = 4;

    public Car() {
    }

    public Car(String brand, int doors, boolean isBroken) {
        this.brand = brand;
        this.doors = doors;
        this.isBroken = isBroken;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add("doors=" + doors)
                .add("isBroken=" + isBroken)
                .toString();
    }
}
