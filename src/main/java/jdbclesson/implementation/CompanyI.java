package jdbclesson.implementation;

import jdbclesson.Company;
import jdbclesson.Connect;
import jdbclesson.dao.CompanyDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CompanyI implements CompanyDAO {

    @Override
    public Company getById(long id) throws SQLException, ClassNotFoundException {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from passengers where id = ?");
             ResultSet resultSet = preparedStatement.executeQuery()){

            return new Company(resultSet.getString(1), resultSet.getString(2));
        }
    }

    @Override
    public Set<Company> getAll() {

        Set<Company> companySet = new HashSet<>();

        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from companies")){

            while (resultSet.next()){
                Company company = new Company(resultSet.getString(1), resultSet.getString(2));
                companySet.add(company);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return companySet;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Company save(Company passenger) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into companies(name, found_date) VALUES (?, ?)")){

            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getFound_date());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passenger;
    }

    @Override
    public Company update(Company passenger) {
        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update companies set name = ?, found_date = ?")){

            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getFound_date());
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return passenger;
    }

    @Override
    public void delete(long companyId) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from companies where id = ?")) {
            preparedStatement.setString(1, String.valueOf(companyId));

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}