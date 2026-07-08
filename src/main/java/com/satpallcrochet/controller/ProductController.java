package com.satpallcrochet.controller;

import com.satpallcrochet.entity.Product;
import com.satpallcrochet.entity.Review;
import com.satpallcrochet.service.ProductService;
import com.satpallcrochet.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        log.info("Viewing product details: {}", id);
        
        Product product = productService.getProductById(id);
        List<Product> relatedProducts = productService.getLatestProducts(4);
        List<Review> reviews = reviewService.getProductReviews(id);
        
        model.addAttribute("product", product);
        model.addAttribute("relatedProducts", relatedProducts);
        model.addAttribute("reviews", reviews);
        
        return "product-detail";
    }

    @PostMapping("/{id}/review")
    public String addReview(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Integer rating) {
        
        log.info("Adding review for product: {}", id);
        reviewService.createReview(id, name, title, content, rating);
        
        return "redirect:/product/" + id + "?reviewAdded=true";
    }

}
