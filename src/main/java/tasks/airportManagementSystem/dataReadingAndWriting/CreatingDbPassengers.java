package tasks.airportManagementSystem.dataReadingAndWriting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class CreatingDbPassengers {
    public static void main(String[] args) throws IOException, SQLException {
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
                String[] passengers = new String[2];
                passengers[0] = s.split(",")[0];
                passengers[1] = s.split(",")[1];
                String insertQuery = getInsertQueryFromStringArray(passengers);
                stmt.executeUpdate(insertQuery);
            }
        }
    }

    public static String getInsertQueryFromStringArray(String[] paramArr) {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO PASSENGERS (NAME,PHONE) VALUES (");
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