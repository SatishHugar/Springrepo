package com.satpallcrochet.controller;

import com.satpallcrochet.dto.CartItemDto;
import com.satpallcrochet.entity.Product;
import com.satpallcrochet.service.ProductService;
import com.satpallcrochet.util.CartManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartManager cartManager;

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity,
            HttpSession session) {
        
        log.info("Adding product to cart: productId={}, quantity={}", productId, quantity);
        
        Product product = productService.getProductById(productId);
        CartItemDto item = new CartItemDto();
        item.setProductId(product.getId());
        item.setProductName(product.getName());
        item.setPrice(product.getPrice());
        item.setQuantity(quantity);
        item.setImage(product.getImage());
        
        cartManager.addToCart(session, item);
        
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        log.info("Viewing cart");
        
        List<CartItemDto> cartItems = cartManager.getCart(session);
        BigDecimal cartTotal = cartManager.getCartTotal(session);
        BigDecimal tax = cartTotal.multiply(new BigDecimal("0.10"));
        BigDecimal shipping = new BigDecimal("100");
        BigDecimal grandTotal = cartTotal.add(tax).add(shipping);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartTotal);
        model.addAttribute("tax", tax);
        model.addAttribute("shipping", shipping);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("itemCount", cartItems.size());
        
        return "cart";
    }

    @PostMapping("/update/{productId}")
    public String updateCartItem(
            @PathVariable Long productId,
            @RequestParam Integer quantity,
            HttpSession session) {
        
        log.info("Updating cart item: productId={}, quantity={}", productId, quantity);
        
        if (quantity > 0) {
            cartManager.updateCartItemQuantity(session, productId, quantity);
        } else {
            cartManager.removeFromCart(session, productId);
        }
        
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, HttpSession session) {
        log.info("Removing product from cart: {}", productId);
        cartManager.removeFromCart(session, productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        log.info("Clearing cart");
        cartManager.clearCart(session);
        return "redirect:/cart";
    }

}
