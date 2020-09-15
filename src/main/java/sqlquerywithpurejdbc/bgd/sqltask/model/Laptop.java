package sqlquerywithpurejdbc.bgd.sqltask.model;

/**
 * Created by User on 13.09.2020.
 */
public class Laptop {
    private int code;
    private String model;
    private double speed;
    private double ram;
    private double hd;
    private float price;
    private int screen;


    public Laptop(int code, String model, double speed, double ram, double hd, float price, int screen) {
        this.code = code;
        this.model = model;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
        this.price = price;
        this.screen = screen;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public double getHd() {
        return hd;
    }

    public void setHd(double hd) {
        this.hd = hd;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }
}
