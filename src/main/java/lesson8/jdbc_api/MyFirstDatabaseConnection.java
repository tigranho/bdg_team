package lesson8.jdbc_api;

import java.sql.*;

public class MyFirstDatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:derby:zoo";
        try (Connection con = DriverManager.getConnection(url);
             Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
            System.out.println(con.getClass());
            System.out.println(stmt.getClass());
            ResultSet rs = stmt.executeQuery("select count(*) from animal");
            while (rs.next()) System.out.println(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
