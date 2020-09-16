package commondbproperties;

import util.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
    Properties properties;
private static final LoadProperties INSTANCE = new LoadProperties();
    public LoadProperties() {
      this.load();
    }

   public String getSettings(String key){
        return properties.getProperty(key);
    }

    void load() {
        this.properties = new Properties();
        InputStream inputStream = LoadProperties.class.getClassLoader().
                getResourceAsStream("application.properties");
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static LoadProperties getInstance(){
        return LoadProperties.INSTANCE;
    }
}
