package jdbc_homework.dao;

import jdbc_homework.models.Company;

import java.util.Set;

public interface CompanyDAO {
    Company getById(long id);

    Set<Company> getAll();

    Set<Company> get(int page, int perPage, String sort);

    Company save(Company company);

    Company update(Company company);

    void delete(long companyId);

}
