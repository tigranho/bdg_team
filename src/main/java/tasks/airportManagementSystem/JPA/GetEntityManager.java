package tasks.airportManagementSystem.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Tatevik Mirzoyan
 * Created on 26-Sep-20
 */
public class GetEntityManager {
    public static EntityManager getEntityManager(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        return entityManagerFactory.createEntityManager();
    }
}
