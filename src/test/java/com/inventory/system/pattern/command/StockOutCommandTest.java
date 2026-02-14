package com.inventory.system.pattern.command;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.entity.*;
import com.inventory.system.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockOutCommandTest {

        @Mock
        private StockRepository stockRepository;
        @Mock
        private AuditLogRepository auditLogRepository;

        private StockRepositoryContext context;

        @BeforeEach
        void setUp() {
                context = StockRepositoryContext.builder()
                                .stockRepository(stockRepository)
                                .auditLogRepository(auditLogRepository)
                                .build();
        }

        @Test
        void execute_ShouldReduceStock_WhenSufficient() {
                StockOperationDTO dto = new StockOperationDTO();
                dto.setProductId(1L);
                dto.setWarehouseId(1L);
                dto.setQuantity(5);
                dto.setReason("Sales");

                Product product = new Product();
                product.setId(1L);
                product.setSku("SKU-1");
                Warehouse warehouse = new Warehouse();
                warehouse.setId(1L);

                Stock stock = new Stock();
                stock.setQuantity(10);
                stock.setProduct(product);
                stock.setWarehouse(warehouse);

                when(stockRepository.findByProductIdAndWarehouseId(1L, 1L)).thenReturn(Optional.of(stock));
                when(stockRepository.save(any(Stock.class))).thenAnswer(i -> i.getArguments()[0]);

                StockOutCommand command = new StockOutCommand(dto, context);
                Stock result = command.execute();

                assertThat(result.getQuantity()).isEqualTo(5);
                verify(auditLogRepository).save(any(AuditLog.class));
        }

        @Test
        void execute_ShouldThrowException_WhenInsufficientStock() {
                StockOperationDTO dto = new StockOperationDTO();
                dto.setProductId(1L);
                dto.setWarehouseId(1L);
                dto.setQuantity(20);

                Stock stock = new Stock();
                stock.setQuantity(10);

                when(stockRepository.findByProductIdAndWarehouseId(1L, 1L)).thenReturn(Optional.of(stock));

                StockOutCommand command = new StockOutCommand(dto, context);

                assertThatThrownBy(command::execute)
                                .isInstanceOf(IllegalStateException.class)
                                .hasMessage("Insufficient stock available");
        }

        @Test
        void execute_ShouldThrowException_WhenStockNotFound() {
                StockOperationDTO dto = new StockOperationDTO();
                dto.setProductId(1L);
                dto.setWarehouseId(1L);

                when(stockRepository.findByProductIdAndWarehouseId(1L, 1L)).thenReturn(Optional.empty());

                StockOutCommand command = new StockOutCommand(dto, context);

                assertThatThrownBy(command::execute)
                                .isInstanceOf(EntityNotFoundException.class);
        }
}
