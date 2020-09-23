package tasks.airportManagementSystem.JDBC.dao;

import tasks.airportManagementSystem.JDBC.model.Address;

import java.sql.SQLException;

/**
 * @author Tatevik Mirzoyan
 * Created on 23-Sep-20
 */
public interface AddressDAO {
    Address save(Address address) throws SQLException;
}
