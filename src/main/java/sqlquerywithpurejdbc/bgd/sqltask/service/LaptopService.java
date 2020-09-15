package sqlquerywithpurejdbc.bgd.sqltask.service;

import java.sql.SQLException;

/**
 * Created by User on 13.09.2020.
 */
public interface LaptopService {
    Double getAvgPrice() throws SQLException;
}
