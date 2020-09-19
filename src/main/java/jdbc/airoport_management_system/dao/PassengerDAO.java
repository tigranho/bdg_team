package jdbc.airoport_management_system.dao;

import jdbc.airoport_management_system.entity.Passenger;
import jdbc.airoport_management_system.entity.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface PassengerDAO {
    Passenger getById(long id) throws SQLException;
    Set<Passenger> getAll();
    Passenger save(Passenger passenger);
    Passenger update(Passenger passenger);
    void delete(long passengerId);
    List<Passenger> getPassengersOfTrip(long tripNumber);
    void registerTrip(Trip trip, Passenger passenger);
    void cancelTrip(long passengerId, long tripNumber);

}
