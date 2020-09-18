package tasks.airportManagementSystem;

import java.io.*;
import java.sql.*;

import static tasks.airportManagementSystem.GetInsertQuery.getInsertQueryFromStringArray;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class CreatingDbCompanies {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/bdg";
        File file = new File("C:\\Tatev Mirzoyan\\JAVA_Intro\\MySQL\\homework(JDBC)\\companies.txt");
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            stmt.executeUpdate(
                    "create table Companies ("
                            + "name varchar(30),"
                            + "found_date DATE)");

            for (int i = 0; i <= 1000; i++) {
                String s = bufferedReader.readLine();
                if (i == 0) continue;
                String[] dates = readFileLineByLine(s);
                String insertQuery = getInsertQueryFromStringArray(dates, "COMPANIES");
                stmt.executeUpdate(insertQuery);
            }
        }
    }

    public static String[] readFileLineByLine(String s) throws IOException {
        String[] str = s.split(",");
        String name = str[0];
        String[] dateArray = str[1].split("/");
        StringBuilder dateString = new StringBuilder();
        if (dateArray.length == 3) {
            dateString.append(dateArray[2]).append(".").append(dateArray[0]).append(".").append(dateArray[1]);
        }
        String[] stringFile = new String[2];
        stringFile[0] = name;
        stringFile[1] = dateString.toString();
        return stringFile;
    }
}
