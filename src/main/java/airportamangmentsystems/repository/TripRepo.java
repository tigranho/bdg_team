package airportamangmentsystems.repository;


import airportamangmentsystems.model.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface TripRepo {
    Trip getById(long id) throws SQLException;

    Set<Trip> getAll() throws SQLException;

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip passenger) throws SQLException;

    Trip update(Trip passenger) throws SQLException;

    void delete(long tripId) throws SQLException;

    List<Trip> getTripsFrom(String city) throws SQLException;

    List<Trip> getTripsTo(String city) throws SQLException;

}
