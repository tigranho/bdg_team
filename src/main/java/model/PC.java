package model;

public class PC {
    private int code;
    private String model;
    private int speed;
    private int hd;
    private int cd;
    private float price;
    private String maker;

    public void setCode(int code) {
        this.code = code;
    }


    public void setModel(String model) {
        this.model = model;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "PC{" +
                "code=" + code +
                ", model='" + model + '\'' +
                ", speed=" + speed +
                ", hd=" + hd +
                ", cd=" + cd +
                ", price=" + price +
                ", maker='" + maker + '\'' +
                '}';
    }
}
