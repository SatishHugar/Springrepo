package com.satpallcrochet.controller;

import com.satpallcrochet.dto.CategoryDto;
import com.satpallcrochet.entity.Category;
import com.satpallcrochet.service.CategoryService;
import com.satpallcrochet.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @GetMapping
    public String listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        log.info("Listing categories - page: {}, size: {}", page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryService.getAllCategories(pageable);
        
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        
        return "admin/categories/list";
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        log.info("Displaying create category form");
        model.addAttribute("categoryDto", new CategoryDto());
        return "admin/categories/form";
    }

    @PostMapping("/create")
    public String createCategory(
            @Valid @ModelAttribute CategoryDto categoryDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file) throws IOException {
        
        log.info("Creating category: {}", categoryDto.getName());
        
        if (bindingResult.hasErrors()) {
            return "admin/categories/form";
        }
        
        if (!file.isEmpty()) {
            String imagePath = fileUploadUtil.uploadFile(file, "categories");
            categoryDto.setImage(imagePath);
        }
        
        categoryService.createCategory(categoryDto);
        return "redirect:/admin/categories?success=true";
    }

    @GetMapping("/{id}/edit")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        log.info("Displaying edit category form: {}", id);
        
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setImage(category.getImage());
        categoryDto.setIsActive(category.getIsActive());
        
        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("isEdit", true);
        
        return "admin/categories/form";
    }

    @PostMapping("/{id}/update")
    public String updateCategory(
            @PathVariable Long id,
            @Valid @ModelAttribute CategoryDto categoryDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file) throws IOException {
        
        log.info("Updating category: {}", id);
        
        if (bindingResult.hasErrors()) {
            return "admin/categories/form";
        }
        
        Category category = categoryService.getCategoryById(id);
        if (!file.isEmpty()) {
            String imagePath = fileUploadUtil.uploadFile(file, "categories");
            categoryDto.setImage(imagePath);
        } else {
            categoryDto.setImage(category.getImage());
        }
        
        categoryService.updateCategory(id, categoryDto);
        return "redirect:/admin/categories?success=true";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        log.info("Deleting category: {}", id);
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories?success=true";
    }

}
