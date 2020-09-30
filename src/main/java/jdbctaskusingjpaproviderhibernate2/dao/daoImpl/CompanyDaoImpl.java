package jdbctaskusingjpaproviderhibernate2.dao.daoImpl;



import jdbctaskusingjpaproviderhibernate2.dao.CompanyDao;
import jdbctaskusingjpaproviderhibernate2.entity.Company;

import javax.persistence.*;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 17.09.2020.
 */
public class CompanyDaoImpl implements CompanyDao {
    @Override
    public Company fetchById(long id) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Company company = entityManager.find(Company.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return company;
    }

    @Override
    public Set<Company> fetchAll() throws SQLException {
        Set<Company> companies = new HashSet<>();
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Company> query =
                entityManager.createQuery("SELECT c FROM Company c", Company.class);
        List<Company> results = query.getResultList();
        companies.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return companies;
    }

    @Override
    public Set<Company> fetch(int page, int perPage, String sort) throws SQLException {
        Set<Company> companies = new HashSet<>();
        int startPoint = perPage*page - perPage;
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Company> query =
                entityManager.createQuery("Select p from Company as p order by :n ASC", Company.class).
                        setParameter("n", sort).
                        setFirstResult(startPoint).setMaxResults(perPage);
        List<Company> results = query.getResultList();
        companies.addAll(results);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return companies;

    }

    @Override
    public Company save(Company company) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return company;
    }

    @Override
    public Company update(Company company) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(company);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
        return company;
    }

    @Override
    public void delete(long companyId) throws SQLException {
        EntityManagerFactory e = Persistence.createEntityManagerFactory("Hibernate_JPA_MYSQL");
        EntityManager entityManager = e.createEntityManager();
        Company company = entityManager.find(Company.class, companyId);
        entityManager.getTransaction().begin();
        entityManager.remove(company);
        entityManager.getTransaction().commit();
        entityManager.close();
        e.close();
    }
}
