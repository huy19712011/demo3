package com.example.demo3.repository;

import com.example.demo3.entity.Category;
import com.example.demo3.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public ArrayList<Category> getCategories() {

        List<Category> categories = em.createQuery("select c from Category c", Category.class).getResultList();
        return new ArrayList<>(categories);
    }

    public Category getCategoryById(Long id) {

        return em.find(Category.class, id);
    }
}
