package hibernate.airport_management_system;

import hibernate.airport_management_system.Service.AddressDaoImpl;
import hibernate.airport_management_system.dao.AddressDao;
import hibernate.airport_management_system.data.DataLoader;
import hibernate.airport_management_system.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_JPA");
        EntityManager entityManager = enfactory.createEntityManager();

        /*DataLoader dataLoader = new DataLoader();
        dataLoader.insertIntoPassenger();*/

        AddressDao addressDao = new AddressDaoImpl();

        /*Address address = addressDao.getById(1);
        System.out.println(address);*/

        Set<Address> addressSet = addressDao.getAll();
        System.out.println(addressSet);

        entityManager.close();
        enfactory.close();
    }
}
