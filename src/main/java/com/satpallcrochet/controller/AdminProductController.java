package com.satpallcrochet.controller;

import com.satpallcrochet.dto.ProductDto;
import com.satpallcrochet.entity.Category;
import com.satpallcrochet.entity.Product;
import com.satpallcrochet.service.CategoryService;
import com.satpallcrochet.service.ProductService;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        log.info("Listing products - page: {}, size: {}", page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getAllProducts(pageable);
        
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        
        return "admin/products/list";
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        log.info("Displaying create product form");
        
        List<Category> categories = categoryService.getAllActiveCategories();
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categories);
        
        return "admin/products/form";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {
        
        log.info("Creating product: {}", productDto.getName());
        
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllActiveCategories();
            model.addAttribute("categories", categories);
            return "admin/products/form";
        }
        
        if (!file.isEmpty()) {
            String imagePath = fileUploadUtil.uploadFile(file, "products");
            productDto.setImage(imagePath);
        }
        
        productService.createProduct(productDto);
        return "redirect:/admin/products?success=true";
    }

    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {
        log.info("Displaying edit product form: {}", id);
        
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllActiveCategories();
        
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setImage(product.getImage());
        productDto.setSpecifications(product.getSpecifications());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setIsFeatured(product.getIsFeatured());
        productDto.setIsActive(product.getIsActive());
        
        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", categories);
        model.addAttribute("isEdit", true);
        
        return "admin/products/form";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {
        
        log.info("Updating product: {}", id);
        
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAllActiveCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("isEdit", true);
            return "admin/products/form";
        }
        
        Product product = productService.getProductById(id);
        if (!file.isEmpty()) {
            String imagePath = fileUploadUtil.uploadFile(file, "products");
            productDto.setImage(imagePath);
        } else {
            productDto.setImage(product.getImage());
        }
        
        productService.updateProduct(id, productDto);
        return "redirect:/admin/products?success=true";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        log.info("Deleting product: {}", id);
        productService.deleteProduct(id);
        return "redirect:/admin/products?success=true";
    }

}
