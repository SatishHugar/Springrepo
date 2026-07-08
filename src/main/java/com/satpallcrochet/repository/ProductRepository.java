package com.satpallcrochet.repository;

import com.satpallcrochet.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByIsActiveTrue(Pageable pageable);

    Page<Product> findByCategoryIdAndIsActiveTrue(Long categoryId, Pageable pageable);

    List<Product> findByIsFeaturedTrueAndIsActiveTrueOrderByCreatedAtDesc();

    List<Product> findByIsActiveTrueOrderByCreatedAtDescLimit(int limit);

    @Query("SELECT p FROM Product p WHERE p.isActive = true AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Product> searchProducts(@Param("searchTerm") String searchTerm, Pageable pageable);

    List<Product> findByIsActiveTrueOrderByRatingDescLimit(int limit);

    List<Product> findByStockGreaterThanAndIsActiveTrueOrderByCreatedAtDesc(Integer stock);

}
