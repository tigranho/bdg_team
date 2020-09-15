package sqlquerywithpurejdbc.bgd.sqltask.model;

/**
 * Created by User on 13.09.2020.
 */
public class Product {
    private String maker;
    private String model;
    private String type;

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }
}
