package jdbctaskusingjpaproviderhibernate2.dao;




import jdbctaskusingjpaproviderhibernate2.entity.Passenger;
import jdbctaskusingjpaproviderhibernate2.entity.Trip;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface PassengerDao {
    Passenger fetchById(long id) throws SQLException;
    Set<Passenger> fetchAll() throws  SQLException;
    Set<Passenger> fetch(int page, int perPage, String sort) throws SQLException;
    Passenger save(Passenger passenger) throws SQLException;
    Passenger update(Passenger passenger) throws SQLException;
    void delete(long passengerId) throws SQLException;
    List<Passenger> getPassengersOfTrip(long tripNumber) throws SQLException;
    void registerTrip(Trip trip, Passenger passenger) throws SQLException;
    void cancelTrip(long passengerId, long tripNumber) throws SQLException;
}
