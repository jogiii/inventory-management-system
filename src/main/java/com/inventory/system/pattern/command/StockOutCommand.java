package com.inventory.system.pattern.command;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockOutCommand implements StockCommand {

    private final StockOperationDTO dto;
    private final StockRepositoryContext context;

    @Override
    public Stock execute() {
        Stock stock = context.getStockRepository()
                .findByProductIdAndWarehouseId(dto.getProductId(), dto.getWarehouseId())
                .orElseThrow(() -> new EntityNotFoundException("Stock entry not found for this product/warehouse"));

        if (stock.getAvailableQuantity() < dto.getQuantity()) {
            throw new IllegalStateException("Insufficient stock available");
        }

        stock.setQuantity(stock.getQuantity() - dto.getQuantity());
        Stock savedStock = context.getStockRepository().save(stock);

        AuditLog log = AuditLog.builder()
                .action("STOCK_OUT")
                .productId(stock.getProduct().getId())
                .productSku(stock.getProduct().getSku())
                .warehouseId(stock.getWarehouse().getId())
                .quantityChanged(dto.getQuantity())
                .reason(dto.getReason())
                .userPerformed("SYSTEM")
                .build();
        context.getAuditLogRepository().save(log);

        return savedStock;
    }
}
