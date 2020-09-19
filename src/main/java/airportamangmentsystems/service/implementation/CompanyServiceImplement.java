package airportamangmentsystems.service.implementation;


import airportamangmentsystems.model.Company;
import airportamangmentsystems.repository.CompanyRepo;
import airportamangmentsystems.repository.implementation.CompanyRepoImplement;
import airportamangmentsystems.service.CompanyService;

import java.sql.SQLException;
import java.util.Set;

public class CompanyServiceImplement implements CompanyService {
    CompanyRepo companyRepo;

    public CompanyServiceImplement() {
        this.companyRepo = new CompanyRepoImplement();
    }

    @Override
    public Company getById(long id) throws SQLException {
        return companyRepo.getById(id);
    }

    @Override
    public Set<Company> getAll() throws SQLException {
        return companyRepo.getAll();
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) throws SQLException {
        return companyRepo.get(page, perPage, sort);
    }

    @Override
    public Company save(Company passenger) throws SQLException {
        return companyRepo.save(passenger);
    }

    @Override
    public Company update(Company passenger) throws SQLException {
        return companyRepo.update(passenger);
    }

    @Override
    public void delete(long companyId) throws SQLException {
        companyRepo.delete(companyId);
    }
}
