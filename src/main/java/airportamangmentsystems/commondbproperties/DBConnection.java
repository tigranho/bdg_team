package airportamangmentsystems.commondbproperties;

import commondbproperties.LoadProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final commondbproperties.DBConnection INSTANCE = new commondbproperties.DBConnection();
    private final String url;
    private final String user;
    private final String password;

    public DBConnection(){
        this.url = LoadProperties.getInstance().getSettings("db.url");
        this.user = LoadProperties.getInstance().getSettings("db.user");
        this.password = LoadProperties.getInstance().getSettings("db.password");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url,this.user,this.password);
    }

    public static commondbproperties.DBConnection getInstance(){
        return INSTANCE;
    }




}
