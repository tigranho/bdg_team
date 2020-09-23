package lesson12.airport_management_system.service;

import lesson12.airport_management_system.entity.Company;

import java.util.Optional;
import java.util.Set;

public interface CompanyService {
    Optional<Company> getCompany(long id);

    Set<Company> findAll();

    Set<Company> getCompany(int page, int perPage, String sort);

    Optional<Company> create(Company company);

    void loadCompaniesInfoFromFileAndCreateAll(String path);

    Optional<Company> edit(Company company);

    void remove(long companyId);
}
