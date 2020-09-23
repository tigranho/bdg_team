package tasks.airportManagementSystem.JDBC.dataReadingAndWriting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Tatevik Mirzoyan
 * Created on 22-Sep-20
 */
public class CreatingDbTrips {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/airport_management_system";
        String pathname = "src/main/resources/trip.txt";
        String query = "INSERT INTO TRIP(COMPANY_ID, TIME_IN, TIME_OUT, CITY_FROM, CITY_TOO)" +
                "VALUES((SELECT ID FROM COMPANIES WHERE COMPANIES.ID = ?),?,?,?,?)";
        File file = new File(pathname);
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement stmt = conn.prepareStatement(query);
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int lineCount = (int) Files.lines(Paths.get(pathname)).count();
            for (int i = 0; i < lineCount; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String[] address = s.split(",");
                stmt.setString(1, address[0]);
                stmt.setString(2, address[1]);
                stmt.setString(3, address[2]);
                stmt.setString(4, address[3]);
                stmt.setString(5, address[4]);
                stmt.executeUpdate();
            }
        }
    }
}
