package updatedjdbctask.service;


import updatedjdbctask.exceptions.DatabaseException;
import updatedjdbctask.model.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface TripService {
    Trip getById(long id) throws SQLException, DatabaseException;
    Set<Trip>getAll() throws SQLException, DatabaseException;
    Set<Trip> get(int page, int perPage, String sort) throws SQLException;
    Trip save(Trip passenger) throws SQLException, DatabaseException;
    Trip update(Trip passenger) throws SQLException, DatabaseException;
    void delete(long tripId) throws SQLException, DatabaseException;
    List<Trip>getTripsFrom(String city) throws SQLException, DatabaseException;
    List<Trip> getTripsTo(String city) throws SQLException, DatabaseException;

}
