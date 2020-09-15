package sqlquerywithpurejdbc.bgd.sqltask.dao.daoimpl;


import sqlquerywithpurejdbc.bgd.sqltask.dao.LapTopDao;
import sqlquerywithpurejdbc.bgd.sqltask.util.DatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 13.09.2020.
 */
public class LapTopDaoImpl implements LapTopDao {
    @Override
    public Double fetchAvgPrice() throws SQLException {
        String q = "select avg(price) as avg from laptop;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(q)) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
