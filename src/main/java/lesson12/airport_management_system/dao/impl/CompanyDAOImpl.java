package lesson12.airport_management_system.dao.impl;

import lesson12.airport_management_system.dao.CompanyDAO;
import lesson12.airport_management_system.entity.Company;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyDAOImpl extends BaseDao implements CompanyDAO {

    public CompanyDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Company> getById(long id) {
        Company company = entityManager.find(Company.class, id);
        if (company != null) System.out.printf("Company by id:%d successfully fetched%n", id);
        else System.err.printf("Company by id:%d not found%n", id);
        return Optional.ofNullable(company);
    }

    @Override
    public Set<Company> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> cq = cb.createQuery(Company.class);
        Root<Company> from = cq.from(Company.class);
        CriteriaQuery<Company> select = cq.select(from);
        return entityManager.createQuery(select)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Company> get(int offset, int limit, String sort) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> cq = cb.createQuery(Company.class);
        Root<Company> from = cq.from(Company.class);
        CriteriaQuery<Company> select = cq.select(from);
        CriteriaQuery<Company> orderQuery = select.orderBy(cb.asc(from.get(sort)));
        return entityManager.createQuery(orderQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Company> save(Company company) {
        execQueryByOneTransaction(em -> em.persist(company));
        return Optional.ofNullable(company);
    }

    @Override
    public boolean saveAll(List<String> companies) {
        execQueryByOneTransaction(em -> {
            em.unwrap(Session.class).setJdbcBatchSize(1000);
            try {
                for (String line : companies) {
                    String[] data = line.split(",");
                    em.persist(new Company(data[0].trim(), LocalDate.parse(data[1].trim(),
                            DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US))));
                }
                em.flush();
            } finally {
                em.unwrap(Session.class).setJdbcBatchSize(null);
            }
        });
        return true;
    }

    @Override
    public Optional<Company> update(Company company) {
        final long id = company.getId();
        Company oldCompany = entityManager.find(Company.class, id);
        if (oldCompany != null) {
            oldCompany.setFoundingDate(company.getFoundingDate());
            oldCompany.setName(company.getName());
            execQueryByOneTransaction(em -> em.persist(oldCompany));
            System.out.printf("Company by id:%d successfully updated%n", id);
        } else System.err.printf("Company by id:%d not found%n", id);
        return Optional.ofNullable(oldCompany);
    }

    @Override
    public void delete(long companyId) {
        Company company = entityManager.find(Company.class, companyId);
        if (company != null) {
            execQueryByOneTransaction(em -> em.remove(company));
            System.out.printf("Company by id:%d successfully deleted%n", companyId);
        } else System.err.printf("Company by id:%d not found%n", companyId);
    }
}
