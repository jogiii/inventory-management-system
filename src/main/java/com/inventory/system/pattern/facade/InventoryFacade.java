package com.inventory.system.pattern.facade;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.Stock;
import com.inventory.system.pattern.command.StockCommand;
import com.inventory.system.pattern.factory.StockOperationFactory;
import com.inventory.system.pattern.observer.StockLevelMonitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryFacade {

    private final StockOperationFactory stockOperationFactory;
    private final StockLevelMonitor stockLevelMonitor;

    @Transactional
    public void processStockOperation(StockOperationDTO operationDTO) {
        // 1. Create Command via Factory
        StockCommand command = stockOperationFactory.createCommand(operationDTO);

        // 2. Execute Command
        Stock updatedStock = command.execute();

        // 3. Trigger Observer (Monitor)
        stockLevelMonitor.onStockChange(updatedStock);
    }
}
