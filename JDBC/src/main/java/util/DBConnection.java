package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = System.getenv("JDBC_DRIVER");
    static final String DB_URL = System.getenv("DB_URL");

    //  Database credentials
    static final String DB_USER = System.getenv("DB_USER");
    static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private DBConnection() {
    }

    private static final DBConnection instance = new DBConnection();

    public static DBConnection getInstance() {
        return instance;
    }

    public static Connection connect() {
        Connection dbConnection = null;

        //Load the driver class
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("DB driver not found: " + e.getMessage());
        }

        //get the connection
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection to DB not established: " + e.getMessage());
        }
        return dbConnection;
    }
}
