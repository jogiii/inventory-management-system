package com.inventory.system.dto;

import lombok.Data;

@Data
public class StockDTO {
    private Long id;
    private Long productId;
    private String productSku;
    private Long warehouseId;
    private Integer quantity;
    private Integer availableQuantity;
}
