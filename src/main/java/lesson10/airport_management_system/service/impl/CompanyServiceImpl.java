package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.CompanyDAO;
import lesson10.airport_management_system.model.Company;
import lesson10.airport_management_system.service.CompanyService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDAO companyDAO;

    public CompanyServiceImpl(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    public Company getCompany(long id) {
        return companyDAO.getById(id).orElse(null);
    }

    @Override
    public Set<Company> findAll() {
        return companyDAO.getAll();
    }

    @Override
    public Set<Company> getCompany(int page, int perPage, String sort) {
        return companyDAO.get(page, perPage, sort);
    }

    @Override
    public Company create(Company company) {
        return companyDAO.save(company).orElse(null);
    }

    @Override
    public void createAll(String path) {
        List<String> lineData = null;
        try (Stream<String> fileContent = Files.lines(Paths.get(path))) {
            lineData = fileContent.skip(1).map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: message: " + e.getMessage());
        }
        companyDAO.saveAll(lineData);
    }

    @Override
    public Company edit(Company company) {
        return companyDAO.update(company).orElse(null);
    }

    @Override
    public void remove(long companyId) {
        companyDAO.delete(companyId);
    }
}
