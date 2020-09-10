package chapter10;

import java.sql.*;

/**
 * @author Tigran
 */
public class MyFirstDatabaseConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/bdg";
        try (Connection conn = DriverManager.getConnection(url, "root", "test1test1");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select name from animal")) {
            while (rs.next())
                System.out.println(rs.getString(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
    }
}
