package com.satpallcrochet.service.impl;

import com.satpallcrochet.entity.Product;
import com.satpallcrochet.entity.Review;
import com.satpallcrochet.exception.ResourceNotFoundException;
import com.satpallcrochet.repository.ProductRepository;
import com.satpallcrochet.repository.ReviewRepository;
import com.satpallcrochet.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Review createReview(Long productId, String name, String title, String content, Integer rating) {
        log.info("Creating review for product: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        Review review = new Review();
        review.setProduct(product);
        review.setReviewerName(name);
        review.setTitle(title);
        review.setContent(content);
        review.setRating(rating);
        review.setIsApproved(false);
        review.setCreatedAt(LocalDateTime.now());
        
        return reviewRepository.save(review);
    }

    @Override
    public Review approveReview(Long reviewId) {
        log.info("Approving review: {}", reviewId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        review.setIsApproved(true);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        log.info("Deleting review: {}", reviewId);
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getProductReviews(Long productId) {
        log.info("Fetching reviews for product: {}", productId);
        return reviewRepository.findByProductIdAndIsApprovedTrue(productId);
    }

    @Override
    public Page<Review> getPendingReviews(Pageable pageable) {
        log.info("Fetching pending reviews");
        return reviewRepository.findByIsApprovedFalse(pageable);
    }

}
