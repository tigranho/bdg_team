package lesson9.jpa.service;

import lesson9.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class CreateEmployee {

    public static void main(String[] args) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Employee employee = new Employee();
        employee.setId(1201);
        employee.setName("Frunz");
        employee.setSalary(40000);
        employee.setDeg("Technical Manager");

        entitymanager.persist(employee);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }
}
