package com.inventory.system.pattern.command;

import com.inventory.system.repository.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StockRepositoryContext {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final AuditLogRepository auditLogRepository;
}
