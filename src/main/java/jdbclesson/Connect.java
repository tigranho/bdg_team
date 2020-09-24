package jdbclesson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static final String url = "jdbc:mysql://localhost:3306/Airport?serverTimezone = UTC";
    private static final String user = "";
    private static final String pass = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, user, pass);
    }
}