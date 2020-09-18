package jdbc.service;

import jdbc.model.Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyService {
    private Company company;


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
