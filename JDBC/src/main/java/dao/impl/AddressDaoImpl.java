package dao.impl;

import dao.AddressDao;
import pojo.Address;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDaoImpl implements AddressDao {

    @Override
    public Address getById(long id) {
        Address address = null;
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Address WHERE address_id=?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            address = new Address(rs.getString("city"), rs.getString("country"));
            return address;
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return address;
    }

    @Override
    public Address save(Address address) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Address (country, city) VALUES (?, ?)")) {
            stmt.setString(1, address.getCountry());
            stmt.setString(2, address.getCity());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
        return address;
    }

    @Override
    public long getTripId(Address address) {
        long id = 0;
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT address_id FROM Address WHERE country=? AND city=?")) {
            stmt.setString(1, address.getCountry());
            stmt.setString(2, address.getCity());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getLong("address_id");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return id;
    }
}
