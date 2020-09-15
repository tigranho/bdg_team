package sqlquerywithpurejdbc.bgd.sqltask.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 13.09.2020.
 */
public interface ProductDao {
    List<String> fetchAllMakers(String type) throws SQLException;

    Map<String, Integer> fetchModelCountByMakers() throws SQLException;

    Map<String, Integer> fetchMakerByTypeCount() throws SQLException;

    List<String> fetchMakersOfLaptopWichDontMakePrinters() throws SQLException;
}
