package airportamangmentsystems.repository;


import airportamangmentsystems.model.Company;

import java.sql.SQLException;
import java.util.Set;

public interface CompanyRepo {
    Company getById(long id) throws SQLException;

    Set<Company> getAll() throws SQLException;

    Set<Company> get(int page, int perPage, String sort) throws SQLException;

    Company save(Company passenger) throws SQLException;

    Company update(Company passenger) throws SQLException;

    void delete(long companyId) throws SQLException;
}
