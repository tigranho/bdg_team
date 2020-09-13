package lesson9.jpa.service;

import lesson9.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager manager = emFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("update lesson9.jpa.entity.Employee e set e.salary = 46000 where e.id = 1201");
        manager.getTransaction().commit();
        Employee employee = manager.find(Employee.class, 1201);
        System.out.println(employee);
        manager.close();
        emFactory.close();
    }
}
