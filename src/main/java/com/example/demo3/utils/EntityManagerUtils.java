package com.example.demo3.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtils {

    public static EntityManager getEntityManager() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        return emf.createEntityManager();
    }
}
