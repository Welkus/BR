package com.manager.br.service;

import com.manager.br.model.CategoryModel;
import com.manager.br.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(String categoryName) {
        CategoryModel category = new CategoryModel();
        category.setCategory(categoryName);
        categoryRepository.save(category);
    }

    public void editCategory(long categoryId, String categoryName) {
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            CategoryModel category = optionalCategory.get();
            category.setCategory(categoryName);
            categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
        }
    }
}
