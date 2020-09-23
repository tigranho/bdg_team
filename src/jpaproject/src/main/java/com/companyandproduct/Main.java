package com.companyandproduct;
import com.companyandproduct.airportservice.entity.Passenger;
import com.companyandproduct.airportservice.commonpackage.Adress;
import com.companyandproduct.airportservice.entity.Trip;
import com.companyandproduct.airportservice.service.Service2;
import com.companyandproduct.company1.commonimplementations.OrderOrder;
import com.companyandproduct.company1.picture.Picture;
import com.companyandproduct.company1.service.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws SQLException {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_Project");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();

//
//                Service2 service2 = new Service2();
//                service2.createCompany("a","s",new Trip(1,"s","s","d","s"));
//                service2.createTrip();
//                service2.createCompany();
//            Service service = new Service();
       // try{
//            OrderOrder orderOrder = new OrderOrder("Aaaa","ssssss");
//
//            Picture picture = new Picture("aaa");
//            entityManager.persist(picture);
//            Book book = new Book("aaa","ssss",picture,orderOrder);
//            entityManager.persist(book);
//
//           // Picture picture = new Picture("aaa");
//            entityManager.persist(picture);
//            entityManager.getTransaction().commit();

//            Book book1 = new Book();
//            Picture picture1 = new Picture();
//            OrderOrder orderOrder1 = new OrderOrder();
//            orderOrder1.setOrder1("aaaa");
//            orderOrder1.setOrder2("aaaaa");
//
//            picture1.setPictureName("aaa");
//            picture1.setBook(book1);
//
//            book1.setAuthor("aaaa");
//            book1.setName("aaa");
//            book1.setPicture(picture1);
//            book1.setOrderOrder(orderOrder1);
//
//            picture1.setBook(book1);
//            entityManager.persist(book1);
//            entityManager.persist(picture1);
//            entityManager.getTransaction().commit();

//        }catch (Exception e){
//            entityManager.getTransaction().rollback();
//        }
//        entityManager.close();
//        entityManagerFactory.close();




       // Service service = new Service();

      //  Service1 service1 = new Service1();
        //service.createBook("aaa","aaaa");
      //  service.createBook("in main method","in glavni method",new Picture("gkjhgjhgjg"),new OrderOrder("main1","main2"));
     //   service.createBook("ssss","aaaaaa",new Picture("jkhghjgjhgjhkg"),new OrderOrder("in main method","in main method"));
        //service.createPicture("sssss");
        //service.createPicture("aaaa");
       // service.createPicture("java");
      //  service.saveBook("aaa","Sssss",1,new OrderOrder("aaaa","sssssss"));
  //  service.saveBookBypictureName("sss","ssass","jkhghjgjhgjhkg",new OrderOrder("sdssssa","fddssd"));
     // service.queries("jkhghjgjhgjhkg");
       // service1.fetch();
      //  service1.fetchByCount();
       // service1.fetchByType();
        //service1.getAvg();
//service1.fetchPrinterAndMaker();
      //  service1.fetchCodeSpeedModelPriceByMaker();
     //service1.fetchGetAvgPrice();
      //  service1.fetchPCAndPrinterByMaker();
     //   service1.fetchLaptopAndNotInPrinterByMaker();
    }
}
