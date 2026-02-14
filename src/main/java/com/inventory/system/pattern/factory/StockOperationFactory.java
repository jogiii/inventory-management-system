package com.inventory.system.pattern.factory;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.pattern.command.*;
import com.inventory.system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockOperationFactory {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final AuditLogRepository auditLogRepository;

    public StockCommand createCommand(StockOperationDTO dto) {
        StockRepositoryContext context = StockRepositoryContext.builder()
                .stockRepository(stockRepository)
                .productRepository(productRepository)
                .warehouseRepository(warehouseRepository)
                .auditLogRepository(auditLogRepository)
                .build();

        if (dto.getType() == StockOperationDTO.OperationType.IN) {
            return new StockInCommand(dto, context);
        } else if (dto.getType() == StockOperationDTO.OperationType.OUT) {
            return new StockOutCommand(dto, context);
        } else {
            throw new IllegalArgumentException("Unknown operation type: " + dto.getType());
        }
    }
}
