package hibernate.airport_management_system.dao;

import hibernate.airport_management_system.entity.Company;

import java.util.Set;

public interface CompanyDao {

    Company getById(long id);
    Set<Company> getAll();
    Set<Company> get(int page, int perPage, String sort);
    Company save(Company company);
    Company update(long id, Company company);
    void delete(long companyId);

}
