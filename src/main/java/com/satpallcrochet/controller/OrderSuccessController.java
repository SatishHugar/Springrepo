package com.satpallcrochet.controller;

import com.satpallcrochet.entity.Order;
import com.satpallcrochet.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class OrderSuccessController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order-success")
    public String orderSuccess(@RequestParam String orderNumber, Model model) {
        log.info("Viewing order success page: {}", orderNumber);
        
        Order order = orderService.getOrderByOrderNumber(orderNumber);
        model.addAttribute("order", order);
        
        return "order-success";
    }

}
