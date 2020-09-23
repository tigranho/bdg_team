package lesson12.airport_management_system.dao.impl;

import lesson12.airport_management_system.dao.TripDAO;
import lesson12.airport_management_system.entity.Company;
import lesson12.airport_management_system.entity.Passenger;
import lesson12.airport_management_system.entity.Trip;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TripDAOImpl extends BaseDao implements TripDAO {

    public TripDAOImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Optional<Trip> getById(long id) {
        Trip trip = entityManager.find(Trip.class, id);
        if (trip != null) System.out.printf("Trip by id:%d successfully fetched%n", id);
        else System.err.printf("Trip by id:%d not found%n", id);
        return Optional.ofNullable(trip);
    }

    @Override
    public Set<Trip> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> from = cq.from(Trip.class);
        CriteriaQuery<Trip> select = cq.select(from);
        return entityManager.createQuery(select)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Trip> get(int offset, int limit, String sort) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> from = cq.from(Trip.class);
        CriteriaQuery<Trip> select = cq.select(from);
        cq.orderBy(cb.asc(from.get(sort)));
        return entityManager.createQuery(select)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Trip> save(Trip trip) {
        execQueryByOneTransaction(em -> em.persist(trip));
        return Optional.ofNullable(trip);
    }

    @Override
    public boolean saveAll(List<String> trips) {
//        final StringBuilder query = new StringBuilder("insert into trip(company_id, time_in, time_out, from_city, to_city)" +
//                " values ");
//        for (String line : trips) {
//            String[] data = line.split(",");
//            query.append("('").append(data[0].trim()).append("', '").append(data[1].trim())
//                    .append("', '").append(data[2].trim()).append("', '").append(data[3].trim())
//                    .append("', '").append(data[4].trim()).append("'),");
//        }
//        query.replace(query.length() - 1, query.length(), ";");
//        execQueryByOneTransaction(em -> em.createNativeQuery(query.toString()).executeUpdate());
        execQueryByOneTransaction(em -> {
            em.unwrap(Session.class).setJdbcBatchSize(1000);
            try {
                for (String line : trips) {
                    String[] data = line.split(",");
                    Company company = em.find(Company.class, Long.parseLong(data[0].trim()));
                    em.persist(new Trip(company, LocalDateTime.parse(data[1].trim()),
                            LocalDateTime.parse(data[2].trim()), data[3].trim(), data[4].trim()));
                }
                em.flush();
            } finally {
                em.unwrap(Session.class).setJdbcBatchSize(null);
            }
        });
        return true;
    }

    @Override
    public Set<Passenger> findPassengersByTripId(long tripId) {
        Trip trip = entityManager.find(Trip.class, tripId);
        return trip == null ? Collections.emptySet() : trip.getPassengers();
    }

    @Override
    public Optional<Trip> update(Trip trip) {
        final long id = trip.getId();
        execQueryByOneTransaction(em -> {
            Trip currentTrip = entityManager.find(Trip.class, id);
            if (currentTrip != null) {
                currentTrip.setFromCity(trip.getFromCity());
                currentTrip.setToCity(trip.getToCity());
                currentTrip.setTimeIn(trip.getTimeIn());
                currentTrip.setTimeOut(trip.getTimeOut());
                System.out.printf("Trip by id:%d successfully updated%n", id);
            } else System.err.printf("Trip by id:%d not found%n", id);
        });
        return Optional.of(trip);
    }

    @Override
    public void delete(long tripId) {
        final Trip trip = entityManager.find(Trip.class, tripId);
        if (trip != null) {
            execQueryByOneTransaction(em -> em.remove(trip));
            System.out.printf("Passenger by id:%d successfully deleted%n", tripId);
        } else System.err.printf("Passenger by id:%d not found%n", tripId);
    }

    @Override
    public Set<Trip> findTripsByPassengerId(long passengerId) {
        final Passenger passenger = entityManager.find(Passenger.class, passengerId);
        return passenger == null || passenger.getTrips() == null ? Collections.emptySet() : passenger.getTrips();
    }

    @Override
    public List<Trip> getTripsByFromCity(String fromCity) {
        return getTripsByCity("from_city", fromCity);
    }

    @Override
    public List<Trip> getTripsByToCity(String toCity) {
        return getTripsByCity("to_city", toCity);
    }


    private List<Trip> getTripsByCity(String attr, String city) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trip> cq = cb.createQuery(Trip.class);
        Root<Trip> from = cq.from(Trip.class);
        cq.select(from);
        cq.where(cb.equal(from.get(attr), city));
        return entityManager.createQuery(cq).getResultList();
    }
}
