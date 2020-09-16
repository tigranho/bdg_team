package lesson10.airport_management_system.dao.impl;

import lesson10.airport_management_system.dao.PassengerDAO;
import lesson10.airport_management_system.model.Address;
import lesson10.airport_management_system.model.Passenger;
import lesson10.airport_management_system.model.Trip;
import lesson10.airport_management_system.util.DBConnector;

import java.sql.*;
import java.util.*;

public class PassengerDAOImpl implements PassengerDAO {
    @Override
    public Optional<Passenger> getById(long id) {
        final String query = "select p.id, p.name, p.phone, a.country, a.city from passenger p " +
                "left join address a on p.id = a.id where p.id = ?";
        Passenger passenger = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                passenger = new Passenger(rs.getString("name"), rs.getString("phone"),
                        new Address(rs.getString("country"), rs.getString("city")));
                passenger.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return Optional.ofNullable(passenger);
    }

    @Override
    public Set<Passenger> getAll() {
        final String query = "select * from passenger p left join address a on p.id = a.id";
        Set<Passenger> passengers = null;
        Passenger passenger = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (passengers == null) passengers = new HashSet<>();
                passenger = new Passenger(rs.getString("name"), rs.getString("phone"),
                        new Address(rs.getString("country"), rs.getString("city")));
                passenger.setId(rs.getLong("id"));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return passengers != null ? passengers : Collections.emptySet();
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        final String query = "select * from passenger p left join address a on p.id = a.id" +
                " order by ? Limit ? offset ?";
        Set<Passenger> passengers = null;
        Passenger passenger = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, sort);
            stmt.setInt(2, page);
            stmt.setInt(3, perPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (passengers == null) passengers = new HashSet<>();
                passenger = new Passenger(rs.getString("name"), rs.getString("phone"),
                        new Address(rs.getString("country"), rs.getString("city")));
                passenger.setId(rs.getLong("id"));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return passengers != null ? passengers : Collections.emptySet();
    }

    @Override
    public Optional<Passenger> save(Passenger passenger) {
        final String passQuery = "insert into passenger (name, phone) values (?, ?)";
        final String addrQuery = "insert into address (id, country, city) values (?, ?, ?)";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(passQuery, Statement.RETURN_GENERATED_KEYS)) {
            con.setAutoCommit(false);
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            ResultSet genKey = null;
            if (stmt.executeUpdate() == 1) {
                genKey = stmt.getGeneratedKeys();
                if (genKey.next())
                    passenger.setId(genKey.getLong(1));
            }
            con.commit();

            PreparedStatement stmt2 = con.prepareStatement(addrQuery, Statement.RETURN_GENERATED_KEYS);
            stmt2.setLong(1, passenger.getId());
            stmt2.setString(2, passenger.getAddress().getCountry());
            stmt2.setString(3, passenger.getAddress().getCity());

            if (stmt2.executeUpdate() == 1) {
                genKey = stmt2.getGeneratedKeys();
                if (genKey.next())
                    passenger.getAddress().setId(genKey.getLong(1));
            }
            con.commit();
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return Optional.of(passenger);
    }

    @Override
    public boolean saveAll(List<String> passengersAndAddresses) {
        StringBuilder passengersQuery = new StringBuilder("insert into passenger (name, phone) values ");
        StringBuilder addressesQuery = new StringBuilder("insert into address (id, country, city) values ");
        try (Connection con = DBConnector.getConnection();
             Statement stmt = con.createStatement()) {
            con.setAutoCommit(false);
            for (String line : passengersAndAddresses) {
                line = line.replaceAll("'", "`");
                String[] data = line.split(",");
                passengersQuery.append("('").append(data[0].trim()).append("', '").append(data[1].trim()).append("'),");
                addressesQuery.append("(default, '").append(data[2].trim()).append("', '").append(data[3].trim()).append("'),");
            }
            passengersQuery.replace(passengersQuery.length() - 1, passengersQuery.length(), ";");
            addressesQuery.replace(addressesQuery.length() - 1, addressesQuery.length(), ";");
            int count = stmt.executeUpdate(passengersQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            System.out.println(count);
            con.commit();
            count = stmt.executeUpdate(addressesQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            System.out.println(count);
            con.commit();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getSQLState());
        }
        return true;
    }

    @Override
    public Optional<Passenger> update(Passenger passenger) {
        final String passQuery = "update passenger set name = ?, phone = ? where id = ?";
        final String addrQuery = "update address set country = ?, city = ? where id = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(passQuery)) {
            con.setAutoCommit(false);
            stmt.setString(1, passenger.getName());
            stmt.setString(2, passenger.getPhone());
            stmt.setLong(3, passenger.getId());

            PreparedStatement stmt2 = con.prepareStatement(addrQuery);
            stmt2.setString(1, passenger.getAddress().getCountry());
            stmt2.setString(2, passenger.getAddress().getCity());
            stmt2.setLong(3, passenger.getId());
            if (stmt.executeUpdate() == 1 && stmt2.executeUpdate() == 1)
                System.out.printf("passenger by id:%d successfully updated%n", passenger.getId());
            con.commit();
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return Optional.of(passenger);
    }

    @Override
    public void delete(long passengerId) {
        final String passQuery = "delete from passenger where id = ?";
        final String addrQuery = "delete from address where id = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(passQuery)) {
            con.setAutoCommit(false);
            stmt.setLong(1, passengerId);

            PreparedStatement stmt2 = con.prepareStatement(addrQuery);
            stmt2.setLong(1, passengerId);
            if (stmt2.executeUpdate() == 1 && stmt.executeUpdate() == 1)
                System.out.printf("passenger by id:%d successfully deleted%n", passengerId);
            con.commit();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Set<Passenger> getPassengersOfTrip(long tripNumber) {
        final String query = "select passenger.id as passenger_id, name, phone, country, city from passenger" +
                " inner join passengers_trips pt on passenger.id = pt.passenger_id " +
                "inner join trip t on pt.trip_id = t.id left join address a on passenger.id = a.id " +
                "where t.id = ?;";
        Set<Passenger> passengers = null;
        Passenger passenger = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, tripNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (passengers == null) passengers = new HashSet<>();
                passenger = new Passenger(rs.getString("name"), rs.getString("phone"),
                        new Address(rs.getString("country"), rs.getString("city")));
                passenger.setId(rs.getLong("passenger_id"));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return passengers != null ? passengers : Collections.emptySet();
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        final String query = "insert into passengers_trips values (?, ?);";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, passenger.getId());
            stmt.setLong(2, trip.getId());
            int count = stmt.executeUpdate();
            if (count == 1) System.out.println("Trip successfully registered!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        final String query = "delete from passengers_trips" +
                " where passenger_id = ? and trip_id = ?";
        Passenger passenger = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, passengerId);
            stmt.setLong(2, tripNumber);
            int count = stmt.executeUpdate();
            if (count == 1) System.out.println("Trip successfully deleted");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
