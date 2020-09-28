package tasks.airportManagementSystem.JPA.dao;

import tasks.airportManagementSystem.JPA.model.Company;

import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 17-Sep-20
 */
public interface CompanyDAO {
    Company getById(int id);

    Set<Company> getAll();

    Set<Company> get(int page, int perPage, String sort);

    Company save(Company company);

    Company update(Company company);

    void delete(int companyId);
}
