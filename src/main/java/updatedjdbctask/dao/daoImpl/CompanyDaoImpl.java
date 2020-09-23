package updatedjdbctask.dao.daoImpl;


import updatedjdbctask.dao.CompanyDao;
import updatedjdbctask.model.Company;
import updatedjdbctask.util.DatabaseConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public class CompanyDaoImpl implements CompanyDao {
    @Override
    public Company fetchById(long id) throws SQLException {
        Company company = new Company();
        String q = "select name, found_date as fd from company where company_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            s.setLong(1, id);
            try (ResultSet resultSet = s.executeQuery()) {
                if (resultSet.next()) {
                    company.setName(resultSet.getString(1));
                    company.setFoundDate(resultSet.getString(2));
                    company.setComapnyId(id);
                }
            }
        }
        return company;
    }

    @Override
    public Set<Company> fetchAll() throws SQLException {
        Set<Company> companySet = new HashSet<>();
        String q = "select * from company;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    Company company = new Company();
                    company = getFromResultSet(resultSet);
                    companySet.add(company);
                }
            }
        }
        return companySet;
    }

    @Override
    public Set<Company> fetch(int page, int perPage, String sort) throws SQLException {
        Set<Company> companies = new HashSet<>();
        int startPoint = perPage * page - perPage;
        String q = "Select * from company order by ? ASC Limit ?, ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement s = conn.prepareStatement(q)) {
            s.setString(1, sort);
            s.setLong(2, startPoint);
            s.setLong(3, perPage);
            try (ResultSet resultSet = s.executeQuery()) {
                while (resultSet.next()) {
                    Company company = new Company();
                    company = getFromResultSet(resultSet);
                    companies.add(company);
                }
            }
        }
        return companies;
    }

    @Override
    public Company save(Company company) throws SQLException {
        String query = String.format("insert into company (name, found_date) values('%s', '%s');",
                company.getName(), company.getFoundDate());
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    company.setComapnyId(resultSet.getLong(1));
                    return company;
                }
            }
        }
        return null;
    }

    @Override
    public Company update(Company company) throws SQLException {
        String query = "UPDATE company set name = ?,  found_date = ? WHERE  company.company_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getFoundDate());
            preparedStatement.setLong(3, company.getComapnyId());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return company;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(long companyId) throws SQLException {
        String query = "delete form compnay where company_id = ?;";
        try (Connection conn = DatabaseConnectionFactory.getInstance().getConnection();
             PreparedStatement p = conn.prepareStatement(query)) {
            p.setLong(1, companyId);
            p.execute();
        }
    }

    private static Company getFromResultSet(ResultSet r) throws SQLException {
        Company company = new Company();
        company.setComapnyId(r.getLong(1));
        company.setName(r.getString(2));
        company.setFoundDate(r.getString(3));
        return company;
    }
}
