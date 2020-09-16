package model;

import java.util.Objects;

public class Printer {
    private int code;
    private String model;
    private char color;
    private String type;
    private float price;
    private String maker;

    public int getCode() {
        return code;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public char getColor() {
        return color;
    }


    public String getType() {
        return type;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Printer{" +
                "code=" + code +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", maker='" + maker + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Printer)) return false;
        Printer printer = (Printer) o;
        return getCode() == printer.getCode() &&
                getColor() == printer.getColor() &&
                Float.compare(printer.getPrice(), getPrice()) == 0 &&
                Objects.equals(getModel(), printer.getModel()) &&
                Objects.equals(getType(), printer.getType()) &&
                Objects.equals(getMaker(), printer.getMaker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getModel(), getColor(), getType(), getPrice(), getMaker());
    }
}
