package com.example.demo3.repository;

import com.example.demo3.entity.Category;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import com.example.demo3.utils.EntityManagerUtils;

public class CategoryRepository {

    public ArrayList<Category> getCategories() {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            List<Category> categories = em.createQuery("select c from Category c", Category.class).getResultList();
            return new ArrayList<>(categories);
        }
    }

    public Category getCategoryById(Long id) {

        try (EntityManager em = EntityManagerUtils.getEntityManager()) {
            return em.find(Category.class, id);
        }
    }
}
