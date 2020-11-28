package hibernate.airport_management_system.data;

import hibernate.airport_management_system.entity.Address;
import hibernate.airport_management_system.entity.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {
    public void insertIntoPassenger() throws IOException {
        BufferedReader companiesReader = new BufferedReader(new FileReader("C:\\Users\\Samvel\\Desktop\\JAVA_Intermediate\\homework(JDBC)\\homework(JDBC)\\passengers.txt"));
        String str;
        List<String> strings = new LinkedList<>();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        while((str = companiesReader.readLine()) != null) {
            strings.add(str);
        }

        entityManager.getTransaction().begin();

        for(String string : strings) {
            String[] s = string.split(",");
            Address address = new Address(s[2], s[3]);
            Passenger passenger = new Passenger(s[0], s[1], address);
            entityManager.persist(address);
            entityManager.persist(passenger);
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
