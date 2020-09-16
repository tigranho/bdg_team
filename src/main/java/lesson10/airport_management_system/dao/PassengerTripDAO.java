package lesson10.airport_management_system.dao;

import java.util.List;

public interface PassengerTripDAO {
    void save(long passengerId, long tripId);

    void delete(long passengerId, long tripId);

    boolean updateTripByPassengerId(long passengerId, long updateTripId);

    boolean updatePassengerByTripId(long updatePassengerId, long tripId);

    void saveAll(List<String> passengersTrips);
}
