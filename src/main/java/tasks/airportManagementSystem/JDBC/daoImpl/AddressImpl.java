package tasks.airportManagementSystem.JDBC.daoImpl;

import tasks.airportManagementSystem.JDBC.dao.AddressDAO;
import tasks.airportManagementSystem.JDBC.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static tasks.airportManagementSystem.JDBC.DbConnection.getConnection;

/**
 * @author Tatevik Mirzoyan
 * Created on 23-Sep-20
 */
public class AddressImpl implements AddressDAO {

    public Address ifExistIgnoreElseAddAddress(Address address) {
        String query = "insert ignore into ADDRESS (country, city) values(?,?)";
        String querySelect = "select id from address where CITY = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, address.getCountry());
            stmt.setString(2, address.getCity());
            stmt.executeUpdate();

            PreparedStatement statement = conn.prepareStatement(querySelect);
            statement.setString(1, address.getCity());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                address.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return address;
    }
}
