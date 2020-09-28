package tasks.airportManagementSystem.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Tatevik Mirzoyan
 * Created on 25-Sep-20
 */
public class DbConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/airport_management_system";
        return DriverManager.getConnection(url, "root", "root");
    }
}
