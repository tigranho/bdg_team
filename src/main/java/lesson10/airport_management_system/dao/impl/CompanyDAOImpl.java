package lesson10.airport_management_system.dao.impl;

import lesson10.airport_management_system.dao.CompanyDAO;
import lesson10.airport_management_system.model.Company;
import lesson10.airport_management_system.util.DBConnector;

import java.sql.*;
import java.util.*;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public Optional<Company> getById(long id) {
        final String query = "select * from company c where c.id = ?";
        Company company = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(company);
    }

    @Override
    public Set<Company> getAll() {
        final String query = "select * from company";
        Set<Company> companies = null;
        Company company = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (companies == null) companies = new HashSet<>();
                company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("id"));
                companies.add(company);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return companies != null ? companies : Collections.emptySet();
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        final String query = "select * from company order by ? Limit ? offset ?";
        Set<Company> companies = null;
        Company company = null;
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, sort);
            stmt.setInt(2, page);
            stmt.setInt(3, perPage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (companies == null) companies = new HashSet<>();
                company = new Company(rs.getString("name"), rs.getDate("founding_date").toLocalDate());
                company.setId(rs.getLong("id"));
                companies.add(company);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return companies != null ? companies : Collections.emptySet();
    }

    @Override
    public Optional<Company> save(Company company) {
        final String query = "insert into company (name, founding_date) values (?, ?)";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, company.getName());
            stmt.setDate(2, java.sql.Date.valueOf(company.getFoundingDate()));
            ResultSet genKey = null;
            if (stmt.executeUpdate() == 1) {
                genKey = stmt.getGeneratedKeys();
                if (genKey.next())
                    company.setId(genKey.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(company);
    }

    @Override
    public boolean saveAll(List<String> companies) {
        StringBuilder query = new StringBuilder("insert into company (name, founding_date) values ");
        try (Connection con = DBConnector.getConnection();
             Statement stmt = con.createStatement()) {
            con.setAutoCommit(false);
            for (String line : companies) {
                String[] data = line.split(",");
                query.append("('").append(data[0].trim()).append("', '").append(data[1].trim()).append("'),");
            }
            query.replace(query.length() - 1, query.length(), ";");
            int count = stmt.executeUpdate(query.toString(), Statement.RETURN_GENERATED_KEYS);
            System.out.println(count);
            con.commit();
        } catch (SQLException e) {
            System.err.println("failed to save data: message: " + e.getMessage());
        }
        return true;
    }

    @Override
    public Optional<Company> update(Company company) {
        final String query = "update company set name = ?, founding_date = ? where id = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            stmt.setString(1, company.getName());
            stmt.setDate(2, java.sql.Date.valueOf(company.getFoundingDate()));
            stmt.setLong(3, company.getId());
            if (stmt.executeUpdate() == 1)
                System.out.printf("company by id:%d successfully updated%n", company.getId());
            con.commit();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(company);
    }

    @Override
    public void delete(long companyId) {
        final String query = "delete from company where id = ?";
        try (Connection con = DBConnector.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, companyId);
            if (stmt.executeUpdate() == 1)
                System.out.printf("company by id:%d successfully deleted%n", companyId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
