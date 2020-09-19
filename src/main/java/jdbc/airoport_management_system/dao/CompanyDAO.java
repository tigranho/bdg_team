package jdbc.airoport_management_system.dao;

import jdbc.airoport_management_system.entity.Company;

import java.util.Set;

public interface CompanyDAO {
    Company getById(long id);
    Set<Company> getAll();
    Set<Company> get(int page, int perPage, String sort);
    Company save(Company passenger);
    Company update(Company passenger);
    void delete(long companyId);

}
