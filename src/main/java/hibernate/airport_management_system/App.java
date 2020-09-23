package hibernate.airport_management_system;

import hibernate.airport_management_system.entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");

        EntityManager entityManager = enfactory.createEntityManager();

        entityManager.close();
        enfactory.close();
    }
}
