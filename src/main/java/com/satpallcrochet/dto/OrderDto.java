package com.satpallcrochet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private String orderNumber;

    private Long customerId;

    private String customerName;

    private String customerEmail;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal shippingCost;

    private BigDecimal discount;

    private BigDecimal grandTotal;

    private String status;

    private String paymentMethod;

    private String notes;

    private String shippingAddress;

    private String shippingCity;

    private String shippingPostalCode;

    private String shippingPhone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<OrderItemDto> orderItems;

}
