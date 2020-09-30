package jdbctaskusingjpaproviderhibernate2.dao;




import jdbctaskusingjpaproviderhibernate2.entity.Company;

import java.sql.SQLException;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public interface CompanyDao {
    Company fetchById(long id) throws SQLException;
    Set<Company> fetchAll() throws SQLException;
    Set<Company> fetch(int page, int perPage, String sort) throws SQLException;
    Company save(Company company) throws SQLException;
    Company update(Company company) throws SQLException;
    void delete(long companyId)throws SQLException;

}
