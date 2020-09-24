package jdbclesson.dao;

import jdbclesson.Passenger;
import jdbclesson.Trip;
import jdbclesson.implementation.TripI;

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

    void registerTrip(TripI trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);
}