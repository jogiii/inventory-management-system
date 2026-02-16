package com.inventory.system.pattern.command;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.*;
import com.inventory.system.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void execute_ShouldUpdateStock_WhenExists() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setProductId(1L);
        dto.setWarehouseId(1L);
        dto.setQuantity(5);

        StockRepositoryContext context = StockRepositoryContext.builder()
                .stockRepository(stockRepository)
                .productRepository(productRepository)
                .warehouseRepository(warehouseRepository)
                .auditLogRepository(auditLogRepository)
                .build();

        Product product = Product.builder().id(1L).sku("SKU").build();
        Stock existingStock = Stock.builder()
                .product(product)
                .warehouse(Warehouse.builder().id(1L).build())
                .quantity(10)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(Warehouse.builder().id(1L).build()));
        when(stockRepository.findByProductIdAndWarehouseId(1L, 1L)).thenReturn(Optional.of(existingStock));
        when(stockRepository.save(any(Stock.class))).thenAnswer(i -> i.getArguments()[0]);

        StockInCommand command = new StockInCommand(dto, context);
        Stock result = command.execute();

        assertThat(result.getQuantity()).isEqualTo(15);
        verify(stockRepository).save(any(Stock.class));
    }

    @Test
    void execute_ShouldThrowException_WhenProductNotFound() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setProductId(1L);

        StockRepositoryContext context = StockRepositoryContext.builder()
                .productRepository(productRepository)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        StockInCommand command = new StockInCommand(dto, context);

        assertThatThrownBy(command::execute)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Product not found");
    }

    @Test
    void execute_ShouldThrowException_WhenWarehouseNotFound() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setProductId(1L);
        dto.setWarehouseId(1L);

        StockRepositoryContext context = StockRepositoryContext.builder()
                .productRepository(productRepository)
                .warehouseRepository(warehouseRepository)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));
        when(warehouseRepository.findById(1L)).thenReturn(Optional.empty());

        StockInCommand command = new StockInCommand(dto, context);

        assertThatThrownBy(command::execute)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Warehouse not found");
    }
}
