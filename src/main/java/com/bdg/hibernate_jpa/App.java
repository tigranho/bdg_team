package com.bdg.hibernate_jpa;

import com.bdg.hibernate_jpa.data.DataLoader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory enfactory = Persistence.createEntityManagerFactory("hibernate_jpa");

        EntityManager entityManager = enfactory.createEntityManager();

        DataLoader dataLoader = new DataLoader();

        dataLoader.insertIntoAddress();

        entityManager.close();
        enfactory.close();
    }
}