package lesson12.airport_management_system.dao.impl;

import lesson12.airport_management_system.dao.PassengerDAO;
import lesson12.airport_management_system.entity.Address;
import lesson12.airport_management_system.entity.Passenger;
import lesson12.airport_management_system.entity.Trip;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

public class PassengerDAOImpl extends BaseDao implements PassengerDAO {

    public PassengerDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Optional<Passenger> getById(long id) {
        Passenger passenger = entityManager.find(Passenger.class, id);
        if (passenger != null) System.out.printf("Passenger by id:%d successfully fetched%n", id);
        else System.err.printf("Passenger by id:%d not found%n", id);
        return Optional.ofNullable(passenger);
    }

    @Override
    public Set<Passenger> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Passenger> cq = cb.createQuery(Passenger.class);
        Root<Passenger> from = cq.from(Passenger.class);
        CriteriaQuery<Passenger> select = cq.select(from);
        return entityManager.createQuery(select)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Passenger> get(int offset, int limit, String sort) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Passenger> cq = cb.createQuery(Passenger.class);
        Root<Passenger> from = cq.from(Passenger.class);
        CriteriaQuery<Passenger> select = cq.select(from);
        CriteriaQuery<Passenger> orderQuery = select.orderBy(cb.asc(from.get(sort)));
        return entityManager.createQuery(orderQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Passenger> save(Passenger passenger) {
        execQueryByOneTransaction(em -> {
            em.persist(passenger);
            em.persist(passenger.getAddress());
        });
        return Optional.of(passenger);
    }

    @Override
    public boolean saveAll(List<String> passengersAndAddresses) {
//        StringBuilder passengersQuery = new StringBuilder("insert into passenger (name, phone) values ");
//        StringBuilder addressesQuery = new StringBuilder("insert into address (country, city) values ");
//        for (String line : passengersAndAddresses) {
//            line = line.replaceAll("'", "`");
//            String[] data = line.split(",");
//            passengersQuery.append("('").append(data[0].trim()).append("', '").append(data[1].trim()).append("'),");
//            addressesQuery.append("(default, '").append(data[2].trim()).append("', '").append(data[3].trim()).append("'),");
//        }
//        passengersQuery.replace(passengersQuery.length() - 1, passengersQuery.length(), ";");
//        addressesQuery.replace(addressesQuery.length() - 1, addressesQuery.length(), ";");
//        execQueryByOneTransaction(em -> {
//            em.createNativeQuery(passengersQuery.toString()).executeUpdate();
//            em.createNativeQuery(addressesQuery.toString()).executeUpdate();
//        });
        execQueryByOneTransaction(em -> {
            em.unwrap(Session.class).setJdbcBatchSize(1001);
            try {
                for (String line : passengersAndAddresses) {
                    String[] data = line.split(",");
                    Address address = entityManager.createQuery(String.format("select a from Address a where a.country = '%s' and a.city = '%s'",
                            data[2].trim(), data[3].trim()), Address.class).getResultStream().findFirst().orElse(null);
                    if (address == null) address = new Address(data[2].trim(), data[3].trim());
                    Passenger passenger = new Passenger(data[0].trim(), data[1].trim(), address);
                    if (address.getPassengers() == null) address.setPassengers(new HashSet<>());
                    passenger.setAddress(address);
                    address.getPassengers().add(passenger);
                    em.persist(address);
                }
                em.flush();
            } finally {
                em.unwrap(Session.class).setJdbcBatchSize(null);
            }
        });
        return true;
    }

    @Override
    public Optional<Passenger> update(Passenger passenger) {
        final long id = passenger.getId();
        execQueryByOneTransaction(em -> {
            Passenger current = entityManager.find(Passenger.class, id);
            if (current != null) {
                current.setName(passenger.getName());
                current.setPhone(passenger.getPhone());
                System.out.printf("Passenger by id:%d successfully updated%n", id);
            } else System.err.printf("Passenger by id:%d not found%n", id);
        });
        return Optional.of(passenger);
    }

    @Override
    public void delete(long passengerId) {
        Passenger passenger = entityManager.find(Passenger.class, passengerId);
        if (passenger != null) {
            execQueryByOneTransaction(em -> em.remove(passenger));
            System.out.printf("Passenger by id:%d successfully deleted%n", passengerId);
        } else System.err.printf("Passenger by id:%d not found%n", passengerId);
    }

    @Override
    public Set<Passenger> getPassengersOfTrip(long tripNumber) {
        Trip trip = entityManager.find(Trip.class, tripNumber);
        return trip == null ? Collections.emptySet() : trip.getPassengers();
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        final long tripId = trip.getId();
        final long passengerId = passenger.getId();
        execQueryByOneTransaction(em -> {
            Trip currentTrip = entityManager.find(Trip.class, tripId);
            Passenger currentPassenger = entityManager.find(Passenger.class, passengerId);
            if (currentTrip != null && currentPassenger != null) {
                if (currentPassenger.getTrips() == null) currentPassenger.setTrips(new HashSet<>());
                if (currentTrip.getPassengers() == null) currentTrip.setPassengers(new HashSet<>());
                currentPassenger.getTrips().add(trip);
                currentTrip.getPassengers().add(passenger);
                System.out.println("Passenger trip successfully registered");
            } else
                System.err.println("Failed to register passenger trip, please check passenger and trip info end try again.");
        });
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        execQueryByOneTransaction(em -> {
            Trip currentTrip = entityManager.find(Trip.class, tripNumber);
            Passenger currentPassenger = entityManager.find(Passenger.class, passengerId);
            if (currentTrip != null && currentPassenger != null) {
                currentPassenger.getTrips().remove(currentTrip);
                currentTrip.getPassengers().remove(currentPassenger);
                System.out.println("Passenger trip successfully canceled");
            } else
                System.err.println("Failed to cancel passenger trip, please check passenger and trip info end try again.");
        });
    }

    @Override
    public void readPassengersTripsInfoAndRegisterAll(List<String> passengersTrips) {
        execQueryByOneTransaction(em -> {
            em.unwrap(Session.class).setJdbcBatchSize(100);
            try {
                for (String line : passengersTrips) {
                    String[] data = line.split(",");
                    Passenger passenger = em.find(Passenger.class, Long.parseLong(data[0].trim()));
                    Trip trip = em.find(Trip.class, Long.parseLong(data[1].trim()));
                    if (passenger != null && trip != null) {
                        if (passenger.getTrips() == null) passenger.setTrips(new HashSet<>());
                        if (trip.getPassengers() == null) trip.setPassengers(new HashSet<>());
                        passenger.getTrips().add(trip);
                        trip.getPassengers().add(passenger);
                        System.out.println("Passenger trip successfully registered");
                    } else
                        System.err.println("Failed to register passenger trip, please check passenger and trip info end try again.");
                }
                em.flush();
            } finally {
                em.unwrap(Session.class).setJdbcBatchSize(null);
            }
        });
    }
}
