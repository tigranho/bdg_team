package hibernate.airport_management_system;

import hibernate.airport_management_system.data.DataLoader;
import hibernate.airport_management_system.entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");

        EntityManager entityManager = enfactory.createEntityManager();

        DataLoader dataLoader = new DataLoader();

        dataLoader.insertIntoAddress();

        entityManager.close();
        enfactory.close();
    }
}
