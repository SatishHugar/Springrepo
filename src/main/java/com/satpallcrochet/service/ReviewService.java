package com.satpallcrochet.service;

import com.satpallcrochet.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    Review createReview(Long productId, String name, String title, String content, Integer rating);

    Review approveReview(Long reviewId);

    void deleteReview(Long reviewId);

    List<Review> getProductReviews(Long productId);

    Page<Review> getPendingReviews(Pageable pageable);

}
