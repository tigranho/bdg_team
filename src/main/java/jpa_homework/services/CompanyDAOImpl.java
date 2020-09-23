package jpa_homework.services;

import jpa_homework.dao.CompanyDAO;
import jpa_homework.models.Company;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public Company getById(long id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Company> typedQuery
                = entityManager.createQuery("select c from Company c where c.id = :id", Company.class);
        typedQuery.setParameter("id", id);
        Company company = typedQuery.getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return company;
    }

    @Override
    public Set<Company> getAll() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Company> typedQuery
                = entityManager.createQuery("select c from Company c", Company.class);
        Set<Company> set = new HashSet<>(typedQuery.getResultList());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return set;
    }

    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        int start = (page - 1) * perPage;
        int end = start + perPage;

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Company> typedQuery
                = entityManager.createQuery("select c from Company c where c.id >= :start and c.id < :end", Company.class);

        typedQuery.setParameter("start", start);
        typedQuery.setParameter("end", end);
        Set<Company> set = new HashSet<>(typedQuery.getResultList());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return set;
    }

    @Override
    public Company save(Company company) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(company);

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return company;
    }

    @Override
    public Company update(Company company) {
//        delete(company.getId());
//        save(company);

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Company> typedQuery =
                entityManager.createQuery("select c from Company c where c.id = :companyID", Company.class);
        typedQuery.setParameter("companyID", company.getId());
        typedQuery.getSingleResult().setFoundingDate(company.getFoundingDate());
        typedQuery.getSingleResult().setName(company.getName());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();

        return company;
    }

    @Override
    public void delete(long companyId) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("delete from Company c where c.id = :companyID");
        query.setParameter("companyID", companyId).executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }
}
