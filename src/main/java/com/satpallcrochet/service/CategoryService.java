package com.satpallcrochet.service;

import com.satpallcrochet.dto.CategoryDto;
import com.satpallcrochet.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);

    Category getCategoryById(Long id);

    Page<Category> getAllCategories(Pageable pageable);

    List<Category> getAllActiveCategories();

    Category getCategoryByName(String name);

}
