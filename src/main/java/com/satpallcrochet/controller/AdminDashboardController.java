package com.satpallcrochet.controller;

import com.satpallcrochet.entity.Order;
import com.satpallcrochet.service.OrderService;
import com.satpallcrochet.service.ProductService;
import com.satpallcrochet.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        log.info("Accessing admin dashboard");
        
        List<Order> pendingOrders = orderService.getOrdersByStatus(Order.OrderStatus.PENDING);
        List<Order> processingOrders = orderService.getOrdersByStatus(Order.OrderStatus.PROCESSING);
        
        BigDecimal totalRevenue = pendingOrders.stream()
                .map(Order::getGrandTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        long totalProducts = 0;
        long totalCategories = 0;
        
        model.addAttribute("pendingOrdersCount", pendingOrders.size());
        model.addAttribute("processingOrdersCount", processingOrders.size());
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalCategories", totalCategories);
        model.addAttribute("recentOrders", pendingOrders);
        
        return "admin/dashboard";
    }

}
