package dao.impl;

import dao.CompanyDao;
import pojo.Company;
import util.DBConnection;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements CompanyDao {
    @Override
    public Company getById(long id) {
        Company company = null;
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Company WHERE company_id=?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            company = new Company(rs.getString("name"), rs.getString("found_date"));
            return company;
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return company;
    }

    @Override
    public Set<Company> getAll() {
        Set<Company> companies = new HashSet<>();
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Company")) {
            while (rs.next()) {
                Company company = new Company(rs.getString("name"), rs.getString("found_date"));
                companies.add(company);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get data: " + e.getMessage());
        }
        return companies;
    }

    // TODO
    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        Set<Company> companies = new HashSet<>();
        return companies;
    }

    @Override
    public Company save(Company company) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Company" +
                     "(name, found_date ) VALUES (?, ?)")) {
            stmt.setString(1, company.getName());
            stmt.setString(2, company.getFound_date());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
        return company;
    }

    @Override
    public Company update(Company company) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Company SET name=?, found_date=?")) {
            stmt.setString(1, company.getName());
            stmt.setString(2, company.getFound_date());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to update data: " + e.getMessage());
        }
        return company;
    }

    @Override
    public void delete(long companyId) {
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Company WHERE company_id=?)")) {
            stmt.setLong(1, companyId);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Failed to delete data: " + e.getMessage());
        }
    }

    public long getCompanyId(Company company) {
        long id = 0;
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT company_id FROM Company WHERE name=?)")) {
            stmt.setString(1, company.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getLong("company_id");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete data: " + e.getMessage());
        }
        return id;
    }
}
