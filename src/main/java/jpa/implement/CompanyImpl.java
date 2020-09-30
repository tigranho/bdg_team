package jpa.implement;

import jpa.GetEntity;
import jpa.entity.Company;
import jpa.dao.CompanyDao;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class CompanyImpl implements CompanyDao {

    @Override
    public Company getById(long id) {
        EntityManager entityManager = GetEntity.getEntityManager();

        return entityManager.find(Company.class, id);
    }

    @Override
    public Set<Company> getAll() {
        Set<Company> companies = new HashSet<>();
        EntityManager entityManager = GetEntity.getEntityManager();

        Company company = (Company) entityManager.createQuery("select Company from Company").getResultList();
        companies.add(company);

        return companies;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        Set<Company> companies = new HashSet<>();
        EntityManager entityManager = GetEntity.getEntityManager();

        Company company = (Company) entityManager.createQuery(
                "select Company from Company order by " + sort)
                .setMaxResults(page).setFirstResult(perPage).getResultList();
        companies.add(company);

        return companies;
    }

    @Override
    public Company save(Company passenger) {
        EntityManager entityManager = GetEntity.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(passenger);
        entityManager.getTransaction().commit();
        return passenger;
    }

    @Override
    public Company update(Company passenger) {
        EntityManager entityManager = GetEntity.getEntityManager();
        Company update_company = entityManager.find(Company.class, passenger.getId());
        entityManager.getTransaction().begin();
        update_company.setName(passenger.getName());
        update_company.setFound_date(passenger.getFound_date());
        entityManager.persist(update_company);
        entityManager.getTransaction().commit();

        return passenger;
    }

    @Override
    public void delete(long companyId) {

        EntityManager entityManager = GetEntity.getEntityManager();
        Company company = entityManager.find(Company.class, companyId);

        entityManager.getTransaction().begin();
        entityManager.remove(company);
        entityManager.getTransaction().commit();
    }
}