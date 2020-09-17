package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.PassengerTripDAO;
import lesson10.airport_management_system.service.PassengerTripService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassengerTripServiceImpl implements PassengerTripService {

    private final PassengerTripDAO passengerTripDAO;

    public PassengerTripServiceImpl(PassengerTripDAO passengerTripDAO) {
        this.passengerTripDAO = passengerTripDAO;
    }

    @Override
    public boolean create(long passengerId, long tripId) {
         return passengerTripDAO.save(passengerId, tripId);
    }

    @Override
    public void loadPassengersTripsInfoFromFileAndCreateAll(String path) {
        List<String> lineData = null;
        try (Stream<String> fileContent = Files.lines(Paths.get(path))) {
            lineData = fileContent.skip(1).map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: message: " + e.getMessage());
        }
        passengerTripDAO.saveAll(lineData != null ? lineData : Collections.emptyList());
    }

    @Override
    public boolean remove(long passengerId, long tripId) {
        return passengerTripDAO.delete(passengerId, tripId);
    }

    @Override
    public boolean editPassengerId(long newPassengerId, long tripId) {
        return passengerTripDAO.updatePassengerByTripId(newPassengerId, tripId);
    }

    @Override
    public boolean editTripId(long passengerId, long newTripId) {
        return passengerTripDAO.updateTripByPassengerId(passengerId, newTripId);
    }

}
