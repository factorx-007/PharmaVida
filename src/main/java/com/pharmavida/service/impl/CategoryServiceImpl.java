package com.pharmavida.service.impl;

import com.pharmavida.dao.CategoryDAO;
import com.pharmavida.model.entity.Category;
import com.pharmavida.service.CategoryService;
import com.pharmavida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;
    private UserService userService;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO, UserService userService) {
        this.categoryDAO = categoryDAO;
        this.userService = userService;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Category getCategoryByCode(String code) {
        return categoryDAO.findByCode(code);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        category.setCreatedBy(userService.getLoggedInUsername());
        category.setLastUpdateBy("-1");
        category.setCreationDate(new Date());
        category.setLastUpdateDate(new Date());
        categoryDAO.save(category);
        return category;
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        category.setLastUpdateBy(userService.getLoggedInUsername());
        category.setLastUpdateDate(new Date());
        categoryDAO.save(category);
        return category;
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryDAO.deleteById(id);
    }

}
