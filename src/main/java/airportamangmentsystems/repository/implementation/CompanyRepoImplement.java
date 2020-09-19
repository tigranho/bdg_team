package airportamangmentsystems.repository.implementation;


import airportamangmentsystems.model.Company;
import airportamangmentsystems.repository.CompanyRepo;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CompanyRepoImplement extends UseSqlConnection implements CompanyRepo {
    @Override
    public Company getById(long id) throws SQLException {
        String query = "select  * from companies where id = ?";
        Company company = null;
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                company = createResultSetFromCompany(resultSet);
            }
        }
        return company == null ? null : company;
    }

    @Override
    public Set<Company> getAll() throws SQLException {
        Set<Company> set = new HashSet<>();
        String query = "select * from companies;";
        try (Connection connection = this.dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                set.add(createResultSetFromCompany(resultSet));
            }
        }
        return set;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) throws SQLException {
        return null;
    }

    @Override
    public Company save(Company passenger) throws SQLException {
        String query = "Insert into companies(name,foundDate)values(?,?) ";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getFoundDate());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    passenger.setId(resultSet.getInt(1));
                }
            }
        }
        return passenger;
    }

    @Override
    public Company update(Company passenger) throws SQLException {
        String query = "update companies set (name,foundDate)values(?,?);";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate(query);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getFoundDate());
        }
        return passenger;
    }

    @Override
    public void delete(long companyId) throws SQLException {
        String query = "delete from companies where companyId = ?;";
        try (Connection connection = this.dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate(query);
        }
    }

    public Company createResultSetFromCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setName(resultSet.getString("name"));
        company.setFoundDate(resultSet.getString("founDdate"));
        return company;
    }
}
