package com.inventory.system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockOperationDTO {
    @NotNull
    private Long productId;

    @NotNull
    private Long warehouseId;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private OperationType type;

    private String reason;

    public enum OperationType {
        IN, OUT
    }
}
