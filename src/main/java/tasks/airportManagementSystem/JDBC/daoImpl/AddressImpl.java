package tasks.airportManagementSystem.JDBC.daoImpl;

import tasks.airportManagementSystem.JDBC.dao.AddressDAO;
import tasks.airportManagementSystem.JDBC.model.Address;
import tasks.airportManagementSystem.JDBC.model.Passenger;
import tasks.airportManagementSystem.JDBC.model.Trip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import static tasks.airportManagementSystem.JDBC.daoImpl.CompanyImpl.getConnection;

/**
 * @author Tatevik Mirzoyan
 * Created on 23-Sep-20
 */
public class AddressImpl implements AddressDAO {

    public Address save(Address address) throws SQLException {
        String query = "insert ignore into ADDRESS (country, city) values(?,?)";
        String querySelect = "select id from address where CITY = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, address.getCountry());
            stmt.setString(2, address.getCity());
            stmt.executeUpdate();

            PreparedStatement statement = getConnection().prepareStatement(querySelect);
            statement.setString(1, address.getCity());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                address.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
}
