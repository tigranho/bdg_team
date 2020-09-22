package jdbc.service;

import jdbc.model.Company;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CompanyService {
    private Company company;

    public Company getById(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/airportsystem", "root", "root");

             PreparedStatement pstmt = conn.prepareStatement("select * from companies where company_id =" + id);
             ResultSet rs = pstmt.executeQuery();) {
            rs.first();
            Company company = new Company(rs.getString("name"), rs.getString("found_date"));
            return company;
        }
    }

    public Set<Company> getAll() throws SQLException {
        Set<Company> companies = new HashSet<>();
        try(Connection conn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/airportsystem", "root", "root");
            PreparedStatement pstmt = conn.prepareStatement("select * from companies");
            ResultSet rs = pstmt.executeQuery();){
            while(rs.next()) {
                Company company = new Company
                        (rs.getString("name"), rs.getString("found_date"));
                companies.add(company);
            }
            return companies;
        }
    }

    public Company save(Company company) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/airportsystem", "root", "root");
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into companies" +
                                "(name, found_date)" +
                                "values (?, ?)"
                );) {
            pstmt.setString(1, company.getName());
            pstmt.setString(2, company.getDate());
            pstmt.execute();
        }
        return company;
    }
}
