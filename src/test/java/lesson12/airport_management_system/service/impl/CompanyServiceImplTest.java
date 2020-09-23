package lesson12.airport_management_system.service.impl;

import lesson12.airport_management_system.dao.impl.CompanyDAOImpl;
import lesson12.airport_management_system.service.CompanyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class CompanyServiceImplTest {

    private static CompanyService service;

    @BeforeAll
    static void setUp() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate_JPA");
        final EntityManager em = emf.createEntityManager();
        service = new CompanyServiceImpl(new CompanyDAOImpl(em));
    }

    @Test
    void getCompany() {
    }

    @Test
    void findAll() {
    }

    @Test
    void testGetCompany() {
    }

    @Test
    void create() {
    }

    @Test
    void edit() {
    }

    @Test
    void remove() {
    }
}