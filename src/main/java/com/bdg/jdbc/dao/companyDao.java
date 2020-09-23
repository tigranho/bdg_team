package com.bdg.jdbc.dao;


import com.bdg.jdbc.models.Company;

import java.util.Set;

public interface companyDao {
    Company getById(long id);

    Set<Company> getAll();

    Set<Company> get(int page, int perPage, String sort);

    Company save(Company company);

    Company update(Company company);

    void delete(long companyId);
}
