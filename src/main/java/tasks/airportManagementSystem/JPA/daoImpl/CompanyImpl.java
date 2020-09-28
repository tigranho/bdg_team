package tasks.airportManagementSystem.JPA.daoImpl;

import tasks.airportManagementSystem.JPA.GetEntityManager;
import tasks.airportManagementSystem.JPA.dao.CompanyDAO;
import tasks.airportManagementSystem.JPA.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Tatevik Mirzoyan
 * Created on 25-Sep-20
 */
public class CompanyImpl implements CompanyDAO {
    @Override
    public Company getById(int id) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        Company company = entityManager.find(Company.class, id);
        entityManager.close();
        return company;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Company> getAll() {
        Set<Company> companies = new LinkedHashSet<>();
        EntityManager entityManager = GetEntityManager.getEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM Company c ");
            List<Company> list = (List<Company>) query.getResultList();
            companies.addAll(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        entityManager.close();
        return companies;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        Set<Company> companies = new LinkedHashSet<>();
        String query = "SELECT c FROM Company c order by c." + sort;
        EntityManager entityManager = GetEntityManager.getEntityManager();
        try {
            List<Company> list = (List<Company>) entityManager
                    .createQuery(query)
                    .setMaxResults(perPage)
                    .setFirstResult(((page - 1) * perPage))
                    .getResultList();
            companies.addAll(list);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        entityManager.close();
        return companies;
    }

    @Override
    public Company save(Company company) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(company);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return company;
    }

    @Override
    public Company update(Company company) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Company updatedCompany = entityManager.find(Company.class, company.getId());
            updatedCompany.setName(company.getName());
            updatedCompany.setFound_date(company.getFound_date());
            entityManager.persist(updatedCompany);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return company;
    }

    @Override
    public void delete(int companyId) {
        EntityManager entityManager = GetEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        try {
            Company company = entityManager.find(Company.class, companyId);
            entityManager.remove(company);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }
}
