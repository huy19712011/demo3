package com.example.demo3;

import com.example.demo3.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        EntityManager em = emf.createEntityManager();

        TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
        List<Product> list = query.getResultList();
        list.forEach(System.out::println);

        //em.createNativeQuery("select * from products", Product.class).getResultList().forEach(System.out::println);
    }
}
