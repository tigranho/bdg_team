import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Company {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:Mysql:@127.0.0.1:3306";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    private String name;
    private String found_date;

    public Company(String name, String found_date) {
        this.name = name;
        this.found_date = found_date;
    }

    public Company getById(long id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Company WHERE company_id='" + id + "'");) {
            Company company = new Company(rs.getString(1), rs.getString(2));
            return company;
        }
    }

    public Set<Company> getAll() throws SQLException {
        Set<Company> companies = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Company");) {
            while (rs.next()) {
                Company company = new Company(rs.getString(1), rs.getString(2));
                companies.add(company);
            }
        }
        return companies;
    }

    // TODO
    public Set<Company> get(int page, int perPage, String sort) {
        Set<Company> companies = new HashSet<>();
        return companies;
    }

    public static Company save(Company company) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO Company" +
                             "(name, phone, found_date )" +
                             "VALUES (?, ?)"
             );
        ) {
            stmt.setString(1, company.name);
            stmt.setString(2, company.found_date);
            stmt.execute();
        }
        return company;
    }

    public Company update(Company company) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE Company SET name=?, found_date=?");) {
            stmt.setString(1, company.name);
            stmt.setString(2, company.found_date);
            stmt.execute();
        }
        return company;
    }

    public void delete(long companyId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Company WHERE company_id=?)");) {
            stmt.setString(1, String.valueOf(companyId));
            stmt.execute();
        }
    }
}
