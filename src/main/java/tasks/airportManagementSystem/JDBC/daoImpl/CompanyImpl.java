package tasks.airportManagementSystem.JDBC.daoImpl;

import tasks.airportManagementSystem.JDBC.dao.CompanyDAO;
import tasks.airportManagementSystem.JDBC.model.Company;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

import static tasks.airportManagementSystem.JDBC.DbConnection.getConnection;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class CompanyImpl implements CompanyDAO {

    @Override
    public Company getById(int id) {
        String query = "select * from companies where companies.id=?";
        Company company = new Company();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                company.setId(id);
                company.setName(rs.getString("name"));
                company.setFound_date(rs.getDate("found_date").toLocalDate());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return company;
    }

    @Override
    public Set<Company> getAll() {
        String query = "select * from companies";
        Set<Company> companySet = new LinkedHashSet<>();
        Company company;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setFound_date(rs.getDate("found_date").toLocalDate());
                companySet.add(company);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return companySet;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        String query = "SELECT * FROM companies ORDER BY " + sort + " LIMIT ? offset ? ";
        Set<Company> companySet = new LinkedHashSet<>();
        Company company;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, perPage);
            stmt.setInt(2, ((page - 1) * perPage));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                company = new Company();
                company.setName(rs.getString("name"));
                company.setId(rs.getInt("id"));
                company.setFound_date(rs.getDate("found_date").toLocalDate());
                companySet.add(company);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return companySet;
    }

    @Override
    public Company save(Company company) {
        String query = "insert into companies(name, found_date) values(?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, company.getName());
            stmt.setString(2, company.getFound_date().toString());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next())
                company.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return company;
    }

    @Override
    public Company update(Company company) {
        String query = "update companies set name = ?, found_date = ? where id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, company.getName());
            stmt.setDate(2, Date.valueOf(company.getFound_date()));
            stmt.setInt(3, company.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return company;
    }

    @Override
    public void delete(int companyId) {
        String query = "delete from companies where id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, companyId);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
