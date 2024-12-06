package com.pharmavida.util;

import com.pharmavida.model.entity.Category;
import com.pharmavida.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<String, Category> {

    private final CategoryService categoryService;

    @Autowired
    public StringToCategoryConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category convert(String source) {
        return categoryService.getCategoryByCode(source);
    }
}