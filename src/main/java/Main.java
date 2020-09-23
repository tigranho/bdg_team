import jpa_homework.models.Company;
import jpa_homework.services.CompanyDAOImpl;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        Company c = new Company("ss", LocalDate.now());
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("airportSystem");
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Company> typedQuery =
                entityManager.createQuery("select c from Company c where c.id = :companyID", Company.class);
        typedQuery.setParameter("companyID", 2);
        typedQuery.getSingleResult().setFoundingDate(c.getFoundingDate());
        typedQuery.getSingleResult().setName(c.getName());

        entityManager.getTransaction().commit();

        entityManager.close();
        emfactory.close();
    }
}