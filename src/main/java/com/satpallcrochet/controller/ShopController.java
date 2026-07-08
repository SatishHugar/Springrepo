package com.satpallcrochet.controller;

import com.satpallcrochet.entity.Category;
import com.satpallcrochet.entity.Product;
import com.satpallcrochet.service.CategoryService;
import com.satpallcrochet.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String shop(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            Model model) {
        
        log.info("Accessing shop page - page: {}, size: {}, categoryId: {}, search: {}", page, size, categoryId, search);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Product> products;
        
        if (search != null && !search.isEmpty()) {
            products = productService.searchProducts(search, pageable);
        } else if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId, pageable);
        } else {
            products = productService.getAllProducts(pageable);
        }
        
        List<Category> categories = categoryService.getAllActiveCategories();
        
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("search", search);
        
        return "shop";
    }

}
