package jpa.implement;

import jpa.entity.Company;
import jpa.dao.CompanyDao;

import java.sql.SQLException;
import java.util.Set;

public class CompanyI implements CompanyDao {


    @Override
    public Company getById(long id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Set<Company> getAll() {
        return null;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Company save(Company passenger) {
        return null;
    }

    @Override
    public Company update(Company passenger) {
        return null;
    }

    @Override
    public void delete(long companyId) {

    }
}