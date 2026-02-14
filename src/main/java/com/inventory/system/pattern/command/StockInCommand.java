package com.inventory.system.pattern.command;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockInCommand implements StockCommand {

    private final StockOperationDTO dto;
    private final StockRepositoryContext context;

    @Override
    public Stock execute() {
        Product product = context.getProductRepository().findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Warehouse warehouse = context.getWarehouseRepository().findById(dto.getWarehouseId())
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found"));

        Stock stock = context.getStockRepository()
                .findByProductIdAndWarehouseId(dto.getProductId(), dto.getWarehouseId())
                .orElse(Stock.builder()
                        .product(product)
                        .warehouse(warehouse)
                        .quantity(0)
                        .reservedQuantity(0)
                        .build());

        stock.setQuantity(stock.getQuantity() + dto.getQuantity());
        Stock savedStock = context.getStockRepository().save(stock);

        AuditLog log = AuditLog.builder()
                .action("STOCK_IN")
                .productId(product.getId())
                .productSku(product.getSku())
                .warehouseId(warehouse.getId())
                .quantityChanged(dto.getQuantity())
                .reason(dto.getReason())
                .userPerformed("SYSTEM")
                .build();
        context.getAuditLogRepository().save(log);

        return savedStock;
    }
}
