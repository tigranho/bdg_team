package jdbclesson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static final String url = "jdbc:mysql://localhost:3306/Airport?serverTimezone = UTC";
    private static final String user = "root";
    private static final String pass = "5202";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, user, pass);
    }
}