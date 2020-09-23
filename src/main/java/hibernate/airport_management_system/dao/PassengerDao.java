package hibernate.airport_management_system.dao;

import hibernate.airport_management_system.entity.Passenger;
import hibernate.airport_management_system.entity.Trip;


import java.util.List;
import java.util.Set;

public interface PassengerDao {

    Passenger getById(long id);
    Set<Passenger> getAll();
    Set<Passenger> get(int page, int perPage, String sort);
    Passenger save(Passenger passenger);
    Passenger update(Passenger passenger, long id);
    void delete(long passengerId);
    List<Passenger> getPassengersOfTrip(long tripNumber);
    void registerTrip(Trip trip, Passenger passenger);
    void cancelTrip(long passengerId, long tripNumber);

}
