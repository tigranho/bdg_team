package jpa.dao;

import jpa.entity.Passenger;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface PassengersDao {

    Passenger getById(long id) throws SQLException, ClassNotFoundException;

    Set<Passenger> getAll();

    Set<Passenger> get(int page, int perPage, String sort);

    Passenger save(Passenger passenger);

    Passenger update(Passenger passenger);

    void delete(long passengerId);

    List<Passenger> getPassengersOfTrip(long tripNumber);
}
