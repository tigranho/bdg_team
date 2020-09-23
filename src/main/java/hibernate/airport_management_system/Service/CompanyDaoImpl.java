package hibernate.airport_management_system.Service;

import hibernate.airport_management_system.dao.CompanyDao;
import hibernate.airport_management_system.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements CompanyDao {

    EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
    EntityManager entityManager = enfactory.createEntityManager();

    @Override
    public Company getById(long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public Set<Company> getAll() {
        return new HashSet<>(entityManager.createQuery("SELECT Company FROM Company ", Company.class).getResultList());
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Company save(Company company) {
        entityManager.persist(company);
        return company;
    }

    @Override
    public Company update(long id, Company company) {
        Company prevAddress = entityManager.find(Company.class, id);

        prevAddress.setName(company.getName());
        prevAddress.setFounding_date(company.getFounding_date());
        return prevAddress;
    }

    @Override
    public void delete(long companyId) {
        Company company = entityManager.find(Company.class, companyId);

        entityManager.remove(company);
    }
}
