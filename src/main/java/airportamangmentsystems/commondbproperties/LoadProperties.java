package airportamangmentsystems.commondbproperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
    Properties properties;
private static final commondbproperties.LoadProperties INSTANCE = new commondbproperties.LoadProperties();
    public LoadProperties() {
      this.load();
    }

   public String getSettings(String key){
        return properties.getProperty(key);
    }

    void load() {
        this.properties = new Properties();
        InputStream inputStream = commondbproperties.LoadProperties.class.getClassLoader().
                getResourceAsStream("application.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static commondbproperties.LoadProperties getInstance(){
        return commondbproperties.LoadProperties.INSTANCE;
    }
}
