package com.inventory.system.pattern.command;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.*;
import com.inventory.system.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockInCommandTest {

    @Mock
    private StockRepository stockRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private WarehouseRepository warehouseRepository;
    @Mock
    private AuditLogRepository auditLogRepository;

    @Test
    void execute_ShouldCreateStock_WhenDoesNotExist() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setProductId(1L);
        dto.setWarehouseId(1L);
        dto.setQuantity(5);
        dto.setReason("New Stock");

        StockRepositoryContext context = StockRepositoryContext.builder()
                .stockRepository(stockRepository)
                .productRepository(productRepository)
                .warehouseRepository(warehouseRepository)
                .auditLogRepository(auditLogRepository)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(Product.builder().id(1L).sku("SKU").build()));
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(Warehouse.builder().id(1L).build()));
        when(stockRepository.findByProductIdAndWarehouseId(1L, 1L)).thenReturn(Optional.empty());
        when(stockRepository.save(any(Stock.class))).thenAnswer(i -> i.getArguments()[0]);

        StockInCommand command = new StockInCommand(dto, context);
        command.execute();

        verify(stockRepository, times(1)).save(any(Stock.class));
        verify(auditLogRepository, times(1)).save(any(AuditLog.class));
    }
}
