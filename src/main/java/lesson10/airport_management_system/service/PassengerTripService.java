package lesson10.airport_management_system.service;

public interface PassengerTripService {
    boolean create(long passengerId, long tripId);

    void loadPassengersTripsInfoFromFileAndCreateAll(String path);

    boolean remove(long passengerId, long tripId);

    boolean editPassengerId(long newPassengerId, long tripId);

    boolean editTripId(long passengerId, long newTripId);

}
