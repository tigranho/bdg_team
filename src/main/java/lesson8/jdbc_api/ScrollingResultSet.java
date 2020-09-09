package lesson8.jdbc_api;

import java.sql.*;

import static java.lang.System.out;

public class ScrollingResultSet {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection( "jdbc:derby:zoo");
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery("select id from species order by id");
            rs.afterLast();
            out.println(rs.previous());
            out.println(rs.getInt(1));
            out.println(rs.previous());
            out.println(rs.getInt(1));
            out.println(rs.last());
            out.println(rs.getInt(1));
            out.println(rs.first());
            out.println(rs.getInt(1));
            rs.beforeFirst();
            out.println(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
