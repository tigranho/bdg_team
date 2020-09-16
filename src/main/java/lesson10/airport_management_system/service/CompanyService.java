package lesson10.airport_management_system.service;

import lesson10.airport_management_system.model.Company;

import java.util.Set;

public interface CompanyService {
    Company getCompany(long id);

    Set<Company> findAll();

    Set<Company> getCompany(int page, int perPage, String sort);

    Company create(Company company);

    void createAll(String path);

    Company edit(Company company);

    void remove(long companyId);
}
