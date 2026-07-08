package com.satpallcrochet.util;

import com.satpallcrochet.dto.CartItemDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartManager {

    private static final String CART_SESSION_KEY = "cart";

    public List<CartItemDto> getCart(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<CartItemDto> cart = (List<CartItemDto>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(HttpSession session, CartItemDto item) {
        List<CartItemDto> cart = getCart(session);
        boolean found = false;
        for (CartItemDto cartItem : cart) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                cartItem.calculateTotalPrice();
                found = true;
                break;
            }
        }
        if (!found) {
            item.calculateTotalPrice();
            cart.add(item);
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void removeFromCart(HttpSession session, Long productId) {
        List<CartItemDto> cart = getCart(session);
        cart.removeIf(item -> item.getProductId().equals(productId));
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void updateCartItemQuantity(HttpSession session, Long productId, Integer quantity) {
        List<CartItemDto> cart = getCart(session);
        for (CartItemDto item : cart) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                item.calculateTotalPrice();
                break;
            }
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void clearCart(HttpSession session) {
        session.setAttribute(CART_SESSION_KEY, new ArrayList<>());
    }

    public BigDecimal getCartTotal(HttpSession session) {
        List<CartItemDto> cart = getCart(session);
        return cart.stream()
                .map(CartItemDto::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getCartItemCount(HttpSession session) {
        return getCart(session).size();
    }

}
