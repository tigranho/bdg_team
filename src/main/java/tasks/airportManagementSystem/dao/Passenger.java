package tasks.airportManagementSystem.dao;

import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public interface Passenger {
    Passenger getById(long id);

    Set<Passenger> getAll();

    Set<Passenger> get(int page, int perPage, String sort);

    Passenger save(Passenger passenger);

    Passenger update(Passenger passenger);

    void delete(long passengerId);

    List<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(long passengerId, long tripNumber);

}
