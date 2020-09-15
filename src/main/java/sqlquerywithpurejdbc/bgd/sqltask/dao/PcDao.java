package sqlquerywithpurejdbc.bgd.sqltask.dao;


import sqlquerywithpurejdbc.bgd.sqltask.model.Pc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by User on 13.09.2020.
 */
public interface PcDao {
    Map<String, Double> fetchAvgPriceEachMaker() throws SQLException;

    Map<String, List<Pc>> getMakersPc() throws SQLException;

    Optional<List<Pc>> fetchAll() throws SQLException;
}
