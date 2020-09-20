package tasks.airportManagementSystem.dataReadingAndWriting;

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
        String pathname = "C:\\Tatev Mirzoyan\\JAVA_Intro\\MySQL\\homework(JDBC)\\companies.txt";
        File file = new File(pathname);
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int lineCount = (int) Files.lines(Paths.get(pathname)).count();
            for (int i = 0; i < lineCount; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String[] data = getStringArrayFromFileContainingDate(s.split(","));
                String insertQuery = getInsertQueryFromStringArray(data);
                stmt.executeUpdate(insertQuery);
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

    public static String getInsertQueryFromStringArray(String[] paramArr) {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO COMPANIES (NAME,FOUND_DATE) VALUES (");
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
