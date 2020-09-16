package lesson10.airport_management_system.service;

public interface PassengerTripService {
    void create(long passengerId, long tripId);

    void createAll(String path);

    void remove(long passengerId, long tripId);

    boolean editPassengerId(long newPassengerId, long tripId);

    boolean editTripId(long passengerId, long newTripId);

}
