package com.inventory.system.pattern.facade;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.Stock;
import com.inventory.system.pattern.command.StockCommand;
import com.inventory.system.pattern.factory.StockOperationFactory;
import com.inventory.system.pattern.observer.StockLevelMonitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryFacadeTest {

    @Mock
    private StockOperationFactory stockOperationFactory;
    @Mock
    private StockLevelMonitor stockLevelMonitor;
    @InjectMocks
    private InventoryFacade inventoryFacade;

    @Test
    void processStockOperation_ShouldExecuteFlow() {
        StockOperationDTO dto = new StockOperationDTO();
        StockCommand command = mock(StockCommand.class);
        Stock stock = new Stock();

        when(stockOperationFactory.createCommand(dto)).thenReturn(command);
        when(command.execute()).thenReturn(stock);

        inventoryFacade.processStockOperation(dto);

        verify(command).execute();
        verify(stockLevelMonitor).onStockChange(stock);
    }
}
