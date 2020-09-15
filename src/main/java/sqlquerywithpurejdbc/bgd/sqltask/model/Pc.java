package sqlquerywithpurejdbc.bgd.sqltask.model;

/**
 * Created by User on 13.09.2020.
 */
public class Pc {
    private int code;
    private String model;
    private double speed;
    private double ram;
    private double hd;
    private String cd;
    private float price;
    private String maker;

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

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
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
        return "Pc{" +
                "code=" + code +
                ", model='" + model + '\'' +
                ", speed=" + speed +
                ", ram=" + ram +
                ", hd=" + hd +
                ", cd='" + cd + '\'' +
                ", price=" + price +
                ", maker='" + maker + '\'' +
                '}';
    }
}
