package com.bdg.hibernate_jpa.dao;
import com.bdg.hibernate_jpa.entity.Company;

import java.util.Set;

public interface CompanyDao {
    Company getById(long id);
    Set<Company> getAll();
    Set<Company> get(int page, int perPage, String sort);
    Company save(Company company);
    Company update(long id, Company company);
    void delete(long companyId);

}
