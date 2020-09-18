package jdbctask.dao;


import jdbctask.model.Company;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface CompanyDao {
    Company fetchById(long id) throws SQLException;
    Set<Company> fetchAll() throws SQLException;
    Set<Company> fetch(int page, int perPage, String sort) throws SQLException;
    Company save(Company passenger) throws SQLException;
    Company update(Company passenger) throws SQLException;
    void delete(long companyId)throws SQLException;

}
