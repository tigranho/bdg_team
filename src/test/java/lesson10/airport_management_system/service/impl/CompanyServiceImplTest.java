package lesson10.airport_management_system.service.impl;

import lesson10.airport_management_system.dao.impl.CompanyDAOImpl;
import lesson10.airport_management_system.model.Company;
import lesson10.airport_management_system.service.CompanyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceImplTest {

    private static CompanyService service;

    @BeforeAll
    static void setUp() {
        service = new CompanyServiceImpl(new CompanyDAOImpl());
    }

    @Test
    void getCompanyById() {
        Company company = service.getCompany(456);
        assertEquals(456, company.getId());
    }

    @Test
    void findAll() {
        Set<Company> companies = service.findAll();
        assertFalse(companies.isEmpty());
    }

    @Test
    void testGetCompanyBySortingAndPaging() {
        Set<Company> companies = service.getCompany(20, 34, "founding_date");
        assertEquals(20, companies.size());
        assertEquals(35, companies.stream().sorted(Comparator.comparingLong(Company::getId)).findAny().get().getId());
    }

    @Test
    void create() {
        Company company = service.create(new Company("Luftganza", LocalDate.of(1983, Month.AUGUST, 7)));
        assertEquals("Luftganza", service.getCompany(company.getId()).getName());
    }

    @Test
    void edit() {
        final String newName = "AirLane";
        final int id = 675;
        Company company = service.getCompany(id);
        company.setName(newName);
        service.edit(company);
        assertEquals(newName, service.getCompany(id).getName());
    }

    @Test
    void remove() {
        final int id = 210;
        Company company = service.getCompany(id);
        assertNotNull(company);
        service.remove(id);
        assertNull(service.getCompany(id));
    }
}