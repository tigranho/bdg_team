package tasks.airportManagementSystem.daoImpl;

import tasks.airportManagementSystem.dao.CompanyDAO;
import tasks.airportManagementSystem.model.Company;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public class CompanyImpl implements CompanyDAO {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/airport_management_system";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        return conn;
    }

    @Override
    public Company getById(int id) {
        String query = "select * from companies where companies.id=?";
        Company company = new Company();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                company.setId(id);
                company.setName(rs.getString("name"));
                company.setFound_date(rs.getDate("found_date").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return company;
    }

    @Override
    public Set<Company> getAll() {
        //todo didn't return in -id- order

        String query = "select * from companies";
        Set<Company> companySet = new HashSet<>();
        Company company = null;
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setFound_date(rs.getDate("found_date").toLocalDate());
                companySet.add(company);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return companySet;
    }


    //TODO
    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Company save(Company company) {
        String query = "insert into companies(name, found_date) values(?,?)";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, company.getName());
            stmt.setString(2, company.getFound_date().toString());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next())
                company.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return company;
    }

    @Override
    public Company update(Company company) {
        String query = "update companies set name = ?, found_date = ? where id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, company.getName());
            stmt.setDate(2, Date.valueOf(company.getFound_date()));
            stmt.setInt(3, company.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return company;
    }

    @Override
    public void delete(int companyId) {
        String query = "delete from companies where id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, companyId);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
