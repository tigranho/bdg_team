package jdbclesson.dao;

import jdbclesson.model.Passenger;
import jdbclesson.model.Trip;

import java.util.List;
import java.util.Set;

public interface TripDAO {

    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip passenger);

    Trip update(Trip passenger);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);
}