package jdbctask.dao;


import jdbctask.model.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface TripDao {
    Trip fetchById(long id) throws SQLException;
    Set<Trip> fetchAll() throws SQLException;
    Set<Trip> fetch(int page, int perPage, String sort) throws SQLException;
    Trip save(Trip passenger) throws SQLException;
    Trip update(Trip passenger) throws SQLException;
    void delete(long tripId) throws SQLException;
    List<Trip> getTripsFrom(String city) throws SQLException;
    List<Trip> getTripsTo(String city) throws SQLException;
}
