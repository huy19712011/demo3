package com.example.demo3.repository;

import com.example.demo3.entity.Product;
import com.example.demo3.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public ArrayList<Product> getProducts() {

        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
        return new ArrayList<>(products);
    }

    public Product getProductById(Long id) {

        return em.find(Product.class, id);
    }

    public void updateProduct(Product product) {

        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }

    public void deleteProduct(long id) {

        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }

    public void addProduct(Product product) {

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }
}
