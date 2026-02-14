package com.inventory.system.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price;
    private Integer lowStockThreshold;
    private Long categoryId;
}
