package jpa.dao;

import jpa.entity.PassengerTrip;

import java.util.Set;

public interface PassengerTripDao {

    PassengerTrip getById(long id);

    Set<PassengerTrip> getAll();

    PassengerTrip save(PassengerTrip trip);

    PassengerTrip update(PassengerTrip trip);

    void delete(long addressId);
}