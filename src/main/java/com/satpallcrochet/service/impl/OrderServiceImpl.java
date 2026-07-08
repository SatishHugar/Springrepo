package com.satpallcrochet.service.impl;

import com.satpallcrochet.dto.OrderDto;
import com.satpallcrochet.entity.Order;
import com.satpallcrochet.entity.OrderItem;
import com.satpallcrochet.entity.Product;
import com.satpallcrochet.exception.ResourceNotFoundException;
import com.satpallcrochet.repository.OrderRepository;
import com.satpallcrochet.repository.ProductRepository;
import com.satpallcrochet.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order createOrder(OrderDto orderDto) {
        log.info("Creating new order");
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setSubtotal(orderDto.getSubtotal());
        order.setTax(orderDto.getTax());
        order.setShippingCost(orderDto.getShippingCost());
        order.setDiscount(orderDto.getDiscount());
        order.setGrandTotal(orderDto.getGrandTotal());
        order.setPaymentMethod(Order.PaymentMethod.valueOf(orderDto.getPaymentMethod()));
        order.setNotes(orderDto.getNotes());
        order.setShippingAddress(orderDto.getShippingAddress());
        order.setShippingCity(orderDto.getShippingCity());
        order.setShippingPostalCode(orderDto.getShippingPostalCode());
        order.setShippingPhone(orderDto.getShippingPhone());
        order.setCreatedAt(LocalDateTime.now());
        
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, OrderDto orderDto) {
        log.info("Updating order: {}", id);
        Order order = getOrderById(id);
        order.setNotes(orderDto.getNotes());
        order.setShippingAddress(orderDto.getShippingAddress());
        order.setShippingCity(orderDto.getShippingCity());
        order.setShippingPostalCode(orderDto.getShippingPostalCode());
        order.setShippingPhone(orderDto.getShippingPhone());
        order.setUpdatedAt(LocalDateTime.now());
        
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("Deleting order: {}", id);
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @Override
    public Order getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with number: " + orderNumber));
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        log.info("Fetching all orders");
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> getCustomerOrders(Long customerId, Pageable pageable) {
        log.info("Fetching orders for customer: {}", customerId);
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    @Override
    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        log.info("Fetching orders with status: {}", status);
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order updateOrderStatus(Long id, Order.OrderStatus status) {
        log.info("Updating order status: id={}, status={}", id, status);
        Order order = getOrderById(id);
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    private String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
