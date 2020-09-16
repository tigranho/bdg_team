import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:Mysql:@127.0.0.1:3306";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void createPassengers() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE DATABASE Passengers (" +
                            "passenger_id INT PRIMARY KEY," +
                            "name VARCHAR (50) NOT NULL," +
                            "phone VARCHAR (50) NOT NULL," +
                            "country VARCHAR (50) NOT NULL," +
                            "city VARCHAR (50) NOT NULL,)"
            );
        }
    }

    public static void loadPassengers() throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("passengers.txt"))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] strInput = s.split(",");
                Passenger passenger = new Passenger(strInput[0], strInput[1], strInput[2], strInput[3]);
                Passenger.save(passenger);
//                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                     PreparedStatement stmt = conn.prepareStatement(
//                             "INSERT INTO Passengers" +
//                                     "(name, phone, country, city)" +
//                                     "VALUES (?, ?, ?, ?)"
//                     );
//                ) {
//                    stmt.setString(1, strInput[0]);
//                    stmt.setString(2, strInput[1]);
//                    stmt.setString(3, strInput[2]);
//                    stmt.setString(4, strInput[3]);
//                    stmt.execute();
//                }
            }
        }
    }

    public static void createCompany() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE DATABASE Company (" +
                            "company_id INT PRIMARY KEY," +
                            "name VARCHAR (50) NOT NULL," +
                            "found_date VARCHAR (50) NOT NULL)"
            );
        }
    }

    public static void loadCompany() throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("companies.txt"))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] strInput = s.split(",");
                Company company = new Company(strInput[0], strInput[1]);
                Company.save(company);
//                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                     PreparedStatement stmt = conn.prepareStatement(
//                             "INSERT INTO Company" +
//                                     "(name, found_date)" +
//                                     "VALUES (?, ?)"
//                     );) {
//                    stmt.setString(1, strInput[0]);
//                    stmt.setString(2, strInput[1]);
//                }
            }
        }
    }

    public static void createTrip() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE DATABASE Trip (" +
                            "trip_id INT PRIMARY KEY," +
                            "tripNumber INT NOT NULL)" +
                            "company_id INT FOREIGN KEY REFERENCES Company(company_id)," +
                            "timeIn VARCHAR (50) NOT NULL)" +
                            "timeOut VARCHAR (50) NOT NULL)" +
                            "destination VARCHAR (50) NOT NULL)" +
                            "origin VARCHAR (50) NOT NULL)"
            );
        }
    }

    public static void createPassengersOfTrip() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            stmt.execute(
                    "CREATE DATABASE PassengersOfTrip (" +
                            "trip_id INT FOREIGN KEY REFERENCES Trip(trip_id)," +
                            "passenger_id INT FOREIGN KEY REFERENCES Passenger(passenger_id),"
            );
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        createPassengers();
        loadPassengers();
        createCompany();
        loadCompany();
        createTrip();
        createPassengersOfTrip();
    }
}
