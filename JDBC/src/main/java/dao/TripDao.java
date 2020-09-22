package dao;

import pojo.Trip;

import java.util.List;
import java.util.Set;

public interface TripDao {
    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip trip);

    Trip update(Trip trip);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);

    long getTripIDByTripNumber(long tripNumber);
}
