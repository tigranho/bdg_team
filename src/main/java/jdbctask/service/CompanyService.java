package jdbctask.service;


import jdbctask.exceptions.DatabaseException;
import jdbctask.model.Company;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface CompanyService {
    Company getById(long id) throws SQLException, DatabaseException;

    Set<Company> getAll() throws SQLException, DatabaseException;

    Set<Company> get(int page, int perPage, String sort) throws SQLException;

    Company save(Company passenger) throws SQLException, DatabaseException;

    Company update(Company passenger) throws SQLException, DatabaseException;

    void delete(long companyId) throws SQLException, DatabaseException;

}
