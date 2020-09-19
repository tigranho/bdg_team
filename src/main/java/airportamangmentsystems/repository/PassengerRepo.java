package airportamangmentsystems.repository;


import airportamangmentsystems.model.Passenger;
import airportamangmentsystems.model.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface PassengerRepo {
    Passenger getById(long id) throws SQLException;

    Set<Passenger> getAll() throws SQLException;

    Set<Passenger> get(int page, int perPage, String sort) throws SQLException;

    Passenger save(Passenger passenger) throws SQLException;

    Passenger update(Passenger passenger) throws SQLException;

    void delete(long passengerId) throws SQLException;

    List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException;

    void registerTrip(Trip trip, Passenger passenger) throws SQLException;

    void cancelTrip(long passengerId, long tripNumber) throws SQLException;
}
