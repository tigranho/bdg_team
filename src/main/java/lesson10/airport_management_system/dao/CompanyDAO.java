package lesson10.airport_management_system.dao;

import lesson10.airport_management_system.model.Company;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CompanyDAO {
    Optional<Company> getById(long id);

    Set<Company> getAll();

    Set<Company> get(int page, int perPage, String sort);

    Optional<Company> save(Company company);

    boolean saveAll(List<String> companies);

    Optional<Company> update(Company company);

    void delete(long companyId);
}
