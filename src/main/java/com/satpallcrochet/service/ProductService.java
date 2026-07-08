package com.satpallcrochet.service;

import com.satpallcrochet.dto.ProductDto;
import com.satpallcrochet.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto productDto);

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> getProductsByCategory(Long categoryId, Pageable pageable);

    Page<Product> searchProducts(String searchTerm, Pageable pageable);

    List<Product> getFeaturedProducts();

    List<Product> getLatestProducts(int limit);

    List<Product> getPopularProducts(int limit);

}
