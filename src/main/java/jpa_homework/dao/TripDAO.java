package jpa_homework.dao;

import jpa_homework.models.Trip;

import java.util.List;
import java.util.Set;

public interface TripDAO {
    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip trip);

    Trip update(Trip trip);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);
}
