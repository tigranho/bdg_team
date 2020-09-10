package chapter10;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bdg";
        Connection conn = DriverManager.getConnection(url, "root", "test1test1");
        Statement stmt = conn.createStatement();


        int result = stmt.executeUpdate(
                "insert into species values(10, 'Deer', 3)");
        System.out.println(result); // 1
        result = stmt.executeUpdate(
                "update species set name = '' where name = 'None'");
        System.out.println(result); // 0
        result = stmt.executeUpdate(
                "delete from species where id = 10");
        System.out.println(result); // 1



        Map<Integer, String> idToNameMap = new HashMap<>();
        ResultSet rs = stmt.executeQuery("select * from species");

        while(rs.next()) {
             int id = rs.getInt("id");
             String name = rs.getString("name");
             idToNameMap.put(id, name);
             }
             System.out.println(idToNameMap); // {1=African Elephant, 2=Zebra}

        String sql = "select * from species";
        boolean isResultSet = stmt.execute(sql);
        if (isResultSet) {
            rs = stmt.getResultSet();
            System.out.println("ran a query");
        } else {
            result = stmt.getUpdateCount();
            System.out.println("ran an update");
        }

        String name = "any";
        PreparedStatement ps = conn.prepareStatement("delete from animal where name = ?");
        ps.setString(1, name);
        ps.execute();

    }
}