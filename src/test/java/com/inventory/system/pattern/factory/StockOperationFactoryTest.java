package com.inventory.system.pattern.factory;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.pattern.command.StockCommand;
import com.inventory.system.pattern.command.StockInCommand;
import com.inventory.system.pattern.command.StockOutCommand;
import com.inventory.system.pattern.command.StockRepositoryContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class StockOperationFactoryTest {

    @Mock
    private StockRepositoryContext context;
    @InjectMocks
    private StockOperationFactory factory;

    @Test
    void createCommand_ShouldReturnStockIn_WhenTypeIn() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setType(StockOperationDTO.OperationType.IN);

        StockCommand command = factory.createCommand(dto);
        assertThat(command).isInstanceOf(StockInCommand.class);
    }

    @Test
    void createCommand_ShouldReturnStockOut_WhenTypeOut() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setType(StockOperationDTO.OperationType.OUT);

        StockCommand command = factory.createCommand(dto);
        assertThat(command).isInstanceOf(StockOutCommand.class);
    }

    @Test
    void createCommand_ShouldThrow_WhenTypeNull() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setType(null);

        assertThatThrownBy(() -> factory.createCommand(dto))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
