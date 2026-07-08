package com.satpallcrochet.service.impl;

import com.satpallcrochet.dto.ProductDto;
import com.satpallcrochet.entity.Category;
import com.satpallcrochet.entity.Product;
import com.satpallcrochet.exception.ResourceNotFoundException;
import com.satpallcrochet.repository.CategoryRepository;
import com.satpallcrochet.repository.ProductRepository;
import com.satpallcrochet.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        log.info("Creating product: {}", productDto.getName());
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImage(productDto.getImage());
        product.setSpecifications(productDto.getSpecifications());
        product.setCategory(category);
        product.setIsFeatured(productDto.getIsFeatured());
        product.setIsActive(productDto.getIsActive());
        product.setCreatedAt(LocalDateTime.now());
        
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        log.info("Updating product: {}", id);
        Product product = getProductById(id);
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setImage(productDto.getImage());
        product.setSpecifications(productDto.getSpecifications());
        product.setCategory(category);
        product.setIsFeatured(productDto.getIsFeatured());
        product.setIsActive(productDto.getIsActive());
        product.setUpdatedAt(LocalDateTime.now());
        
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Deleting product: {}", id);
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        log.info("Fetching all active products");
        return productRepository.findByIsActiveTrue(pageable);
    }

    @Override
    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        log.info("Fetching products for category: {}", categoryId);
        return productRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable);
    }

    @Override
    public Page<Product> searchProducts(String searchTerm, Pageable pageable) {
        log.info("Searching products with term: {}", searchTerm);
        return productRepository.searchProducts(searchTerm, pageable);
    }

    @Override
    public List<Product> getFeaturedProducts() {
        log.info("Fetching featured products");
        return productRepository.findByIsFeaturedTrueAndIsActiveTrueOrderByCreatedAtDesc();
    }

    @Override
    public List<Product> getLatestProducts(int limit) {
        log.info("Fetching latest products: limit={}", limit);
        return productRepository.findByIsActiveTrueOrderByCreatedAtDescLimit(limit);
    }

    @Override
    public List<Product> getPopularProducts(int limit) {
        log.info("Fetching popular products: limit={}", limit);
        return productRepository.findByIsActiveTrueOrderByRatingDescLimit(limit);
    }

}
