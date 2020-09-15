package tasks.database;

import java.sql.*;

/**
 * @author Tatevik Mirzoyan
 * Created on 15-Sep-20
 */
public class FirstConnectionSQL {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/schema_1";
        try (Connection con = DriverManager.getConnection(url, "root", "root");
             Statement st = con.createStatement();
             ResultSet res = st.executeQuery("select Age from bdg")) {
            while (res.next())
                System.out.println(res.getString(1));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
