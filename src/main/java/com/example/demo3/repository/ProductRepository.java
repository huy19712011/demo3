package com.example.demo3.repository;

import com.example.demo3.entity.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.example.demo3.utils.EntityManagerUtils;

public class ProductRepository {

    public ArrayList<Product> getProducts() {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
            return new ArrayList<>(products);
        }
    }

    public Product getProductById(Long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            return em.find(Product.class, id);
        }
    }

    public void updateProduct(Product product) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        }
    }

    public void deleteProduct(long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            em.getTransaction().begin();
            Product p = em.find(Product.class, id);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        }
    }

    public void addProduct(Product product) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }
    }

    //JPQL with index/named params
    public List<Product> getProductByNameOrId_2(String name, long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            TypedQuery<Product> query =
                    em.createQuery("SELECT p FROM Product p WHERE p.name=?1 OR p.id=?2", Product.class);
            query.setParameter(1, name);
            query.setParameter(2, id);
            return query.getResultList();
        }
    }

    public List<Product> getProductByNameOrId_3(String name, long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            TypedQuery<Product> query =
                    em.createQuery("SELECT p FROM Product p WHERE p.name=:name OR p.id=:id", Product.class);
            query.setParameter("name", name);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }

    // Native
    public List<Product> getProductByNameOrId_4(String name, long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            Query query =
                    em.createNativeQuery("SELECT * FROM product p WHERE p.name=?1 OR p.id=?2", Product.class);
            query.setParameter(1, name);
            query.setParameter(2, id);
            return query.getResultList();
        }
    }

    // warning, but works!
    public List<Product> getProductByNameOrId_5(String name, long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            Query query =
                    em.createNativeQuery("SELECT * FROM product p WHERE p.name=:name OR p.id=:id", Product.class);
            query.setParameter("name", name);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }


    //

    //public List<Product> getProductByNameOrId_6(String name, long id);
    public List<Product> getProductByNameOrId_6(String name, long id) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            TypedQuery<Product> query = em.createNamedQuery("Product.getProductByNameOrId_6", Product.class);
            query.setParameter(1, name);
            query.setParameter(2, id);
            return query.getResultList();
        }
    }

    public List<Product> findProductsByNameContaining(String name) {
        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class);
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        }
    }

}
