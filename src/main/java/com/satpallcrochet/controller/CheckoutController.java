package com.satpallcrochet.controller;

import com.satpallcrochet.dto.CartItemDto;
import com.satpallcrochet.dto.CustomerDto;
import com.satpallcrochet.entity.Customer;
import com.satpallcrochet.entity.Order;
import com.satpallcrochet.entity.OrderItem;
import com.satpallcrochet.service.OrderService;
import com.satpallcrochet.util.CartManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartManager cartManager;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String checkout(HttpSession session, Model model) {
        log.info("Accessing checkout page");
        
        List<CartItemDto> cartItems = cartManager.getCart(session);
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        
        BigDecimal subtotal = cartManager.getCartTotal(session);
        BigDecimal tax = subtotal.multiply(new BigDecimal("0.10"));
        BigDecimal shipping = new BigDecimal("100");
        BigDecimal grandTotal = subtotal.add(tax).add(shipping);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("tax", tax);
        model.addAttribute("shipping", shipping);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("customerDto", new CustomerDto());
        
        return "checkout";
    }

    @PostMapping("/process")
    public String processCheckout(
            @Valid @ModelAttribute CustomerDto customerDto,
            BindingResult bindingResult,
            @RequestParam String paymentMethod,
            @RequestParam(required = false) String notes,
            HttpSession session,
            Model model) {
        
        log.info("Processing checkout");
        
        if (bindingResult.hasErrors()) {
            return checkout(session, model);
        }
        
        List<CartItemDto> cartItems = cartManager.getCart(session);
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        
        BigDecimal subtotal = cartManager.getCartTotal(session);
        BigDecimal tax = subtotal.multiply(new BigDecimal("0.10"));
        BigDecimal shipping = new BigDecimal("100");
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal grandTotal = subtotal.add(tax).add(shipping).subtract(discount);
        
        Order order = new Order();
        order.setSubtotal(subtotal);
        order.setTax(tax);
        order.setShippingCost(shipping);
        order.setDiscount(discount);
        order.setGrandTotal(grandTotal);
        order.setPaymentMethod(Order.PaymentMethod.valueOf(paymentMethod));
        order.setNotes(notes);
        order.setShippingAddress(customerDto.getAddress());
        order.setShippingCity(customerDto.getCity());
        order.setShippingPostalCode(customerDto.getPostalCode());
        order.setShippingPhone(customerDto.getPhone());
        
        Order createdOrder = orderService.createOrder(new com.satpallcrochet.dto.OrderDto(
                order.getId(),
                order.getOrderNumber(),
                null,
                customerDto.getFullName(),
                customerDto.getEmail(),
                subtotal,
                tax,
                shipping,
                discount,
                grandTotal,
                order.getStatus().toString(),
                paymentMethod,
                notes,
                customerDto.getAddress(),
                customerDto.getCity(),
                customerDto.getPostalCode(),
                null,
                order.getCreatedAt(),
                order.getUpdatedAt(),
                null
        ));
        
        cartManager.clearCart(session);
        session.setAttribute("lastOrderNumber", createdOrder.getOrderNumber());
        
        return "redirect:/order-success?orderNumber=" + createdOrder.getOrderNumber();
    }

}
