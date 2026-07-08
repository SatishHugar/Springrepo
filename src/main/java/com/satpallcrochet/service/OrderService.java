package com.satpallcrochet.service;

import com.satpallcrochet.dto.OrderDto;
import com.satpallcrochet.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderDto orderDto);

    Order updateOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);

    Order getOrderById(Long id);

    Order getOrderByOrderNumber(String orderNumber);

    Page<Order> getAllOrders(Pageable pageable);

    Page<Order> getCustomerOrders(Long customerId, Pageable pageable);

    List<Order> getOrdersByStatus(Order.OrderStatus status);

    Order updateOrderStatus(Long id, Order.OrderStatus status);

}
