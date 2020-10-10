package com.bdg.hibernate_jpa.service;

import com.bdg.hibernate_jpa.dao.PassengerDao;
import com.bdg.hibernate_jpa.entity.Trip;
import com.bdg.jdbc.models.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {

    EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_jpa");
    EntityManager entityManager = enfactory.createEntityManager();

    @Override
    public Passenger getById(long id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    public Set<com.bdg.hibernate_jpa.entity.Passenger> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT Passenger FROM Passenger ",com.bdg.hibernate_jpa.entity.Passenger.class).getResultList());
    }


    @Override
    public Set<com.bdg.hibernate_jpa.entity.Passenger> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public com.bdg.hibernate_jpa.entity.Passenger save(com.bdg.hibernate_jpa.entity.Passenger passenger) {
        return null;
    }

    @Override
    public com.bdg.hibernate_jpa.entity.Passenger update(com.bdg.hibernate_jpa.entity.Passenger passenger, long id) {
        return null;
    }

    public Passenger save(Passenger passenger) {
        entityManager.persist(passenger);
        return passenger;
    }

    public Passenger update(Passenger passenger, long id) {
        Passenger prevPassenger = entityManager.find(Passenger.class, id);

        prevPassenger.setName(passenger.getName());
        prevPassenger.setPhone(passenger.getPhone());

        return prevPassenger;
    }

    @Override
    public void delete(long passengerId) {
        Passenger passenger = entityManager.find(Passenger.class, passengerId);

        entityManager.remove(passenger);
    }

    @Override
    public List<com.bdg.hibernate_jpa.entity.Passenger> getPassengersOfTrip(long tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, com.bdg.hibernate_jpa.entity.Passenger passenger) {

    }

    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}
