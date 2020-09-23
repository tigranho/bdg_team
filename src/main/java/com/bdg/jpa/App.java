package com.bdg.jpa;

import com.bdg.jpa.entity.Author;
import com.bdg.jpa.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("booksystem-mysql");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        try {

            Author author = new Author ("AAAA",LocalDate.of(1990,01,01));

            entitymanager.persist(author);

            Book book = new Book("Vichakax", author, 450, LocalDate.of(1960, 01, 01));

            entitymanager.persist(book);
            entitymanager.getTransaction().commit();
        }catch (Exception e){
            entitymanager.getTransaction().rollback();
        }

        entitymanager.close();
        emfactory.close();
    }
}
