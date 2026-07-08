package com.satpallcrochet.controller;

import com.satpallcrochet.entity.Banner;
import com.satpallcrochet.entity.Category;
import com.satpallcrochet.entity.Product;
import com.satpallcrochet.repository.BannerRepository;
import com.satpallcrochet.service.CategoryService;
import com.satpallcrochet.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BannerRepository bannerRepository;

    @GetMapping("/")
    public String home(Model model) {
        log.info("Accessing home page");
        
        List<Banner> banners = bannerRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
        List<Category> categories = categoryService.getAllActiveCategories();
        List<Product> featuredProducts = productService.getFeaturedProducts();
        List<Product> latestProducts = productService.getLatestProducts(6);
        List<Product> popularProducts = productService.getPopularProducts(6);
        
        model.addAttribute("banners", banners);
        model.addAttribute("categories", categories);
        model.addAttribute("featuredProducts", featuredProducts);
        model.addAttribute("latestProducts", latestProducts);
        model.addAttribute("popularProducts", popularProducts);
        
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        log.info("Accessing about page");
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        log.info("Accessing contact page");
        return "contact";
    }

    @GetMapping("/faq")
    public String faq() {
        log.info("Accessing FAQ page");
        return "faq";
    }

    @GetMapping("/privacy")
    public String privacy() {
        log.info("Accessing privacy policy page");
        return "privacy";
    }

    @GetMapping("/terms")
    public String terms() {
        log.info("Accessing terms page");
        return "terms";
    }

}
