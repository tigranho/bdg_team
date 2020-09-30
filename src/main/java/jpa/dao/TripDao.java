package jpa.dao;

import jpa.entity.Passenger;
import jpa.entity.Trip;
import jpa.implement.TripImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TripDao {

    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip passenger);

    Trip update(Trip passenger);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);

    void registerTrip(TripImpl trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);
}