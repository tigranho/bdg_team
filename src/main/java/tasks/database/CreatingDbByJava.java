package tasks.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created on 15-Sep-20
 *
 * @author Tatevik Mirzoyan
 */
public class CreatingDbByJava {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/schema_1";
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement statement = conn.createStatement()) {

            statement.executeUpdate(
                    "create table bdg ("
                            + "ID INTEGER primary key,"
                            + "FirstName varchar(30),"
                            + "LastName varchar (50),"
                            + "Age INTEGER)");

            statement.executeUpdate(" INSERT  INTO bdg values (1,'Ashot', 'Ashotyan', 38)");
            statement.executeUpdate(" INSERT  INTO bdg values (2,'Poxos', 'Poxosyan', 42)");
            statement.executeUpdate(" INSERT  INTO bdg values (3,'Petros', 'Petrosyan', 22)");

        }
    }
}
