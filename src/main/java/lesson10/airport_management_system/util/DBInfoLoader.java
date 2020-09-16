package lesson10.airport_management_system.util;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class DBInfoLoader {
    private Properties prop = new Properties();

    private DBInfoLoader() {
        try {
            prop.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("airport_management_system_source/db.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProp() {
        return DBInfoLoaderCreator.INFO_LOADER.prop;
    }

    private static class DBInfoLoaderCreator {
        private static final DBInfoLoader INFO_LOADER = new DBInfoLoader();
    }
}
