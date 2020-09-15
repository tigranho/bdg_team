package sqlquerywithpurejdbc.bgd.sqltask.dao;

import java.sql.SQLException;

/**
 * Created by User on 13.09.2020.
 */
public interface LapTopDao {
    Double fetchAvgPrice() throws SQLException;
}
