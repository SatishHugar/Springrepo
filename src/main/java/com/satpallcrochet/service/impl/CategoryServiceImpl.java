package com.satpallcrochet.service.impl;

import com.satpallcrochet.dto.CategoryDto;
import com.satpallcrochet.entity.Category;
import com.satpallcrochet.exception.ResourceNotFoundException;
import com.satpallcrochet.repository.CategoryRepository;
import com.satpallcrochet.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        log.info("Creating category: {}", categoryDto.getName());
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(categoryDto.getImage());
        category.setIsActive(categoryDto.getIsActive());
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        log.info("Updating category: {}", id);
        Category category = getCategoryById(id);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(categoryDto.getImage());
        category.setIsActive(categoryDto.getIsActive());
        category.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        log.info("Deleting category: {}", id);
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        log.info("Fetching all categories");
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> getAllActiveCategories() {
        log.info("Fetching all active categories");
        return categoryRepository.findByIsActiveTrue();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + name));
    }

}
