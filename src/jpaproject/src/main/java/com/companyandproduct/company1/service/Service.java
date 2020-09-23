package com.companyandproduct.company1.service;

import com.companyandproduct.company1.book.Book;
import com.companyandproduct.company1.commonimplementations.OrderOrder;
import com.companyandproduct.company1.picture.Picture;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.Path;
import java.beans.Expression;


public class Service {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA_Project");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    Book book;
    Picture picture;

    public Book createBook(String name, String author, Picture picture, OrderOrder orderOrder) {
        entityManager.getTransaction().begin();

        book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPicture(picture);
        book.setOrderOrder(orderOrder);
        picture.setBook(book);
        entityManager.persist(picture);
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return book;

    }

    public Picture createPicture(String author) {
        entityManager.getTransaction().begin();
        picture = new Picture();
        picture.setPictureName(author);
        picture.setBook(book);
        entityManager.persist(picture);
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return picture;
    }

    public Book saveBook(String name, String author, int pictureId, OrderOrder orderOrder) {
        Picture picture = entityManager.find(Picture.class, pictureId);
        entityManager.getTransaction().begin();
        book = new Book();
        book.setAuthor(author);
        book.setName(name);
        book.setOrderOrder(orderOrder);
        book.setPicture(picture);
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return book;
    }


    public Book saveBookBypictureName(String name, String author, String pictureName, OrderOrder orderOrder) {


        Picture picture = entityManager.find(Picture.class,2);
        pictureName = picture.getPictureName();
        entityManager.getTransaction().begin();
        book = new Book();
        book.setAuthor(author);
       book.setName(name);
       book.setOrderOrder(orderOrder);
       picture.setPictureName(pictureName);
       book.setPicture(picture);
       entityManager.getTransaction().commit();

        return book;
    }

}
