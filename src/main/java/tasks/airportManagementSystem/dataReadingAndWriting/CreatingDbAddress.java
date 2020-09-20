package tasks.airportManagementSystem.dataReadingAndWriting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Tatevik Mirzoyan
 * Created on 20-Sep-20
 */
public class CreatingDbAddress {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/airport_management_system";
        String pathname = "C:\\Tatev Mirzoyan\\JAVA_Intro\\MySQL\\homework(JDBC)\\passengers.txt";
        File file = new File(pathname);
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int lineCount = (int) Files.lines(Paths.get(pathname)).count();
            for (int i = 0; i < lineCount; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String[] address = new String[2];
                address[0] = s.split(",")[2];
                address[1] = s.split(",")[3];
                String insertQuery = getInsertQueryFromStringArray(address);
                stmt.executeUpdate(insertQuery);
            }
        }
    }

    public static String getInsertQueryFromStringArray(String[] paramArr) {
        StringBuilder insertQuery = new StringBuilder("INSERT IGNORE INTO address (country,city) VALUES (");
        for (int i = 0; i < paramArr.length; i++) {
            insertQuery.append("'").append(paramArr[i]).append("'");
            if (i == paramArr.length - 1) {
                insertQuery.append(")");
            } else {
                insertQuery.append(",");
            }
        }
        return insertQuery.toString();

    }
}
