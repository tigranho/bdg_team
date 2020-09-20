package tasks.airportManagementSystem.dao;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public interface Trip {
    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int page, int perPage, String sort);

    Trip save(Trip passenger);

    Trip update(Trip passenger);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);

}
