package com.pharmavida.service;

import com.pharmavida.model.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByCode(String code);
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}


