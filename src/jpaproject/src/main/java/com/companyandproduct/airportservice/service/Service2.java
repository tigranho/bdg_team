package com.companyandproduct.airportservice.service;

import com.companyandproduct.airportservice.commonpackage.Adress;
import com.companyandproduct.airportservice.entity.Company;
import com.companyandproduct.airportservice.entity.Passenger;
import com.companyandproduct.airportservice.entity.Trip;
import com.companyandproduct.company1.book.Book;
import com.companyandproduct.company1.commonimplementations.OrderOrder;
import com.companyandproduct.company1.picture.Picture;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Service2 {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_Company");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    Company company;
  public Company createCompany(String name,String foundDate,Trip trip){
      entityManager.getTransaction().begin();
      company = new Company();
      company.setName(name);
      company.setFoundDate(foundDate);
      company.setTrip(trip);
      trip.setCompany(company);
      entityManager.persist(trip);
      entityManager.persist(company);
      entityManager.getTransaction().commit();
      return company;
  }




    public Trip createTrip(){
        entityManager.getTransaction().begin();

        Trip trip = new Trip();
        trip.setTownFrom("a");
        trip.setTimeOut("s");
        trip.setTimeIn("a");
        trip.setNumber(1);
        trip.setCompany(company);
        trip.setTownTo("ssss");
        company.setTrip(trip);
        entityManager.persist(trip);
        entityManager.persist(company);
        entityManager.getTransaction().commit();
        return trip;
    }


}
