package airportamangmentsystems.repository.implementation;

import commondbproperties.DBConnection;

public class UseSqlConnection {
    DBConnection dbConnection;

    protected UseSqlConnection() {
        this.dbConnection = DBConnection.getInstance();
    }
}
