package jdbclesson.implementation;

import jdbclesson.model.Company;
import jdbclesson.Connect;
import jdbclesson.dao.CompanyDAO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CompanyImpl implements CompanyDAO {

    @Override
    public Company getById(long id) {

        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from companies where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new Company(resultSet.getInt("id"),
                        resultSet.getString(2), resultSet.getString(3));
            }


        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return null;
    }

    @Override
    public Set<Company> getAll() {

        Set<Company> companySet = new HashSet<>();

        try (Connection connection = Connect.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from companies");

            while (resultSet.next()) {

                Company company = new Company(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("found_date"));
                companySet.add(company);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return companySet;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {

        Set<Company> companies = new TreeSet<>();

        try (Connection connection = Connect.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from companies order by ? limit ? offset ?");

            preparedStatement.setInt(page, 1);
            preparedStatement.setInt(perPage, 2);
            preparedStatement.setString(3, sort);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Company company = new Company(resultSet.getInt("id"),
                        resultSet.getString(2), resultSet.getString(3));

                companies.add(company);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
        return companies;
    }

    @Override
    public Company save(Company passenger) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into companies(id, name, found_date) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getFound_date());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }

        return passenger;
    }

    @Override
    public Company update(Company passenger) {
        try (Connection connection = Connect.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update companies set id = ?, name = ?, found_date = ?");

            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.setString(3, passenger.getFound_date());
            preparedStatement.executeUpdate();
            return passenger;

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void delete(long companyId) {

        try (Connection connection = Connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from companies where id = ?")) {
            preparedStatement.setLong(1, companyId);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}