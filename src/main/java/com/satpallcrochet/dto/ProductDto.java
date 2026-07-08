package com.satpallcrochet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @Positive(message = "Stock must be greater than 0")
    private Integer stock;

    private String image;

    private String specifications;

    private Long categoryId;

    private String categoryName;

    private Boolean isFeatured = false;

    private Boolean isActive = true;

    private Double rating = 0.0;

    private Integer reviewCount = 0;

}
