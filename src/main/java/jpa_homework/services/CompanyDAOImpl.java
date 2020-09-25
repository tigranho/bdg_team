package jpa_homework.services;

import jpa_homework.dao.CompanyDAO;
import jpa_homework.models.Company;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public Company getById(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        Company company = entityManager.find(Company.class, id);

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
        int start = (page == 0) ? (page - 1) * perPage : (page - 1) * perPage - 1;

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> query = cb.createQuery(Company.class);
        Root<Company> from = query.from(Company.class);
        CriteriaQuery<Company> select = query.select(from);
        CriteriaQuery<Company> orderBy = select.orderBy(cb.asc(from.get(sort)));
        Set<Company> set = entityManager.createQuery(orderBy)
                .setFirstResult(start)
                .setMaxResults(perPage)
                .getResultStream()
                .collect(Collectors.toSet());

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
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(company);

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
