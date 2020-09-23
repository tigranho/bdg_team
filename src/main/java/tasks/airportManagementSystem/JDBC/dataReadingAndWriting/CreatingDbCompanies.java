package tasks.airportManagementSystem.JDBC.dataReadingAndWriting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class CreatingDbCompanies {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/airport_management_system";
        String pathname = "src/main/resources/companies.txt";
        String query = "INSERT INTO COMPANIES (NAME,FOUND_DATE) VALUES (?, ?)";
        File file = new File(pathname);
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement stmt = conn.prepareStatement(query);
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int lineCount = (int) Files.lines(Paths.get(pathname)).count();
            for (int i = 0; i < lineCount; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String[] data = getStringArrayFromFileContainingDate(s.split(","));
                stmt.setString(1, data[0]);
                stmt.setString(2, data[1]);
                stmt.executeUpdate();
            }
        }
    }

    public static String[] getStringArrayFromFileContainingDate(String[] s) {
        String[] str = new String[s.length];
        str[0] = s[0];
        DateTimeFormatter f = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(s[1], f);
        str[1] = date.format(f1);
        return str;
    }
}
