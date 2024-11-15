package com.example.demo3.service;

import com.example.demo3.entity.Category;
import com.example.demo3.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    CategoryRepository categoryRepository = new CategoryRepository();

    public ArrayList<Category> getCategories() {

        return categoryRepository.getCategories();
    }

    public Category getCategoryById(Long id) {

        return categoryRepository.getCategoryById(id);
    }
}
