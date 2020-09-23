package hibernate.airport_management_system.dao;

import hibernate.airport_management_system.entity.Trip;

import java.util.List;
import java.util.Set;

public interface TripDao {
    Trip getById(long id);
    Set<Trip> getAll();
    Set<Trip> get(int page, int perPage, String sort);
    Trip save(Trip trip);
    Trip update(Trip trip, long id);
    void delete(long tripId);
    List<Trip> getTripsFrom(String city);
    List<Trip> getTripsTo(String city);

}
