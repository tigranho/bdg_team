package tasks.airportManagementSystem.JDBC.dataReadingAndWriting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

/**
 * @author Tatevik Mirzoyan
 * Created on 20-Sep-20
 */
public class CreatingDbAddress {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/airport_management_system";
        String pathname = "src/main/resources/passengers.txt";
        String query = "INSERT IGNORE INTO ADDRESS (country,city) VALUES (?, ?)";
        File file = new File(pathname);
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement stmt = conn.prepareStatement(query);
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int lineCount = (int) Files.lines(Paths.get(pathname)).count();
            for (int i = 0; i < lineCount; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String[] address = s.split(",");
                stmt.setString(1, address[2]);
                stmt.setString(2, address[3]);
                stmt.executeUpdate();
            }
        }
    }
}
