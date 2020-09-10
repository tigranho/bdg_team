package chapter10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Tigran
 */
public class SetupDerbyDatabase {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/bdg";
        try (Connection conn = DriverManager.getConnection(url, "root", "test1test1");
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE species ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL)");
            stmt.executeUpdate(
                    "CREATE TABLE animal ("
                            + "id INTEGER PRIMARY KEY, "
                            + "species_id integer, "
                            + "name VARCHAR(255), "
                            + "date_born TIMESTAMP)");

            stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
            stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");
            stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2020-12-09 11:13:48')");
            stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2020-11-09 11:13:48')");
            stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2020-10-08 11:13:48')");
            stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2020-08-07 11:13:48')");
            stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2020-07-06 11:13:48')");
        }
    }
}
