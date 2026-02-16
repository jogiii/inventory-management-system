package com.inventory.system.pattern.command;

import com.inventory.system.repository.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class StockRepositoryContextTest {

    @Test
    void contextBuilderAndGetters_Coverage() {
        StockRepository sr = mock(StockRepository.class);
        ProductRepository pr = mock(ProductRepository.class);
        WarehouseRepository wr = mock(WarehouseRepository.class);
        AuditLogRepository alr = mock(AuditLogRepository.class);

        StockRepositoryContext context = StockRepositoryContext.builder()
                .stockRepository(sr)
                .productRepository(pr)
                .warehouseRepository(wr)
                .auditLogRepository(alr)
                .build();

        assertThat(context.getStockRepository()).isEqualTo(sr);
        assertThat(context.getProductRepository()).isEqualTo(pr);
        assertThat(context.getWarehouseRepository()).isEqualTo(wr);
        assertThat(context.getAuditLogRepository()).isEqualTo(alr);
    }
}
