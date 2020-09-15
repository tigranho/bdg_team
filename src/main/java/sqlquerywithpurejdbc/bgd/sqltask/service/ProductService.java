package sqlquerywithpurejdbc.bgd.sqltask.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 13.09.2020.
 */
public interface ProductService {
    List<String> getMakersByType(String type) throws SQLException;

    Map<String, Integer> getModelCountByMakers() throws SQLException;

    Map<String, Integer> getMakersByTypeCount() throws SQLException;

    List<String> getMakersOfLaptopWichDontMakePrinters() throws SQLException;
}
