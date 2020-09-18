package tasks.airportManagementSystem;

import java.io.*;
import java.sql.*;

import static tasks.airportManagementSystem.GetInsertQuery.getInsertQueryFromStringArray;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class CreatingDbPassengers {
    public static void main(String[] args) throws IOException, SQLException {
        String url = "jdbc:mysql://localhost:3306/bdg";
        File file = new File("C:\\Tatev Mirzoyan\\JAVA_Intro\\MySQL\\homework(JDBC)\\passengers.txt");
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            stmt.executeUpdate(
                    "create table Passengers("
                            + "Name VARCHAR(50),"
                            + "Phone VARCHAR(50),"
                            + "Country VARCHAR(50),"
                            + "City VARCHAR(50))");

            for (int i = 0; i <= 1000; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String insertQuery = getInsertQueryFromStringArray(s.split(","), "Passengers");
                stmt.executeUpdate(insertQuery);
            }
        }
    }


}