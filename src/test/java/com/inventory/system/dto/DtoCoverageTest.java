package com.inventory.system.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

class DtoCoverageTest {

    @Test
    void categoryDtoCoverage() {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(1L);
        dto.setName("Name");
        dto.setDescription("Desc");

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Name");
        assertThat(dto.getDescription()).isEqualTo("Desc");

        CategoryDTO dto2 = new CategoryDTO();
        dto2.setId(1L);
        dto2.setName("Name");
        dto2.setDescription("Desc");

        assertThat(dto).isEqualTo(dto2);
        assertThat(dto).hasSameHashCodeAs(dto2);
        assertThat(dto.toString()).contains("Name");
    }

    @Test
    void productDtoCoverage() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("Name");
        dto.setSku("SKU");
        dto.setDescription("Desc");
        dto.setPrice(BigDecimal.TEN);
        dto.setLowStockThreshold(5);
        dto.setCategoryId(2L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Name");
        assertThat(dto.getSku()).isEqualTo("SKU");
        assertThat(dto.getDescription()).isEqualTo("Desc");
        assertThat(dto.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(dto.getLowStockThreshold()).isEqualTo(5);
        assertThat(dto.getCategoryId()).isEqualTo(2L);

        ProductDTO dto2 = new ProductDTO();
        dto2.setId(1L);
        dto2.setName("Name");
        dto2.setSku("SKU");
        dto2.setDescription("Desc");
        dto2.setPrice(BigDecimal.TEN);
        dto2.setLowStockThreshold(5);
        dto2.setCategoryId(2L);

        assertThat(dto).isEqualTo(dto2);
        assertThat(dto).hasSameHashCodeAs(dto2);
        assertThat(dto.toString()).contains("Name");
    }

    @Test
    void stockOperationDtoCoverage() {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setProductId(1L);
        dto.setWarehouseId(2L);
        dto.setQuantity(10);
        dto.setType(StockOperationDTO.OperationType.IN);
        dto.setReason("Reason");

        assertThat(dto.getProductId()).isEqualTo(1L);
        assertThat(dto.getWarehouseId()).isEqualTo(2L);
        assertThat(dto.getQuantity()).isEqualTo(10);
        assertThat(dto.getType()).isEqualTo(StockOperationDTO.OperationType.IN);
        assertThat(dto.getReason()).isEqualTo("Reason");

        StockOperationDTO dto2 = new StockOperationDTO();
        dto2.setProductId(1L);
        dto2.setWarehouseId(2L);
        dto2.setQuantity(10);
        dto2.setType(StockOperationDTO.OperationType.IN);
        dto2.setReason("Reason");

        assertThat(dto).isEqualTo(dto2);
        assertThat(dto).hasSameHashCodeAs(dto2);
        assertThat(dto.toString()).contains("Reason");
    }

    @Test
    void stockDtoCoverage() {
        StockDTO dto = new StockDTO();
        dto.setId(1L);
        dto.setQuantity(100);
        dto.setAvailableQuantity(90);
        dto.setProductId(1L);
        dto.setProductSku("SKU");
        dto.setWarehouseId(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getQuantity()).isEqualTo(100);
        assertThat(dto.getAvailableQuantity()).isEqualTo(90);
        assertThat(dto.getProductId()).isEqualTo(1L);
        assertThat(dto.getProductSku()).isEqualTo("SKU");
        assertThat(dto.getWarehouseId()).isEqualTo(1L);

        StockDTO dto2 = new StockDTO();
        dto2.setId(1L);
        dto2.setQuantity(100);
        dto2.setAvailableQuantity(90);
        dto2.setProductId(1L);
        dto2.setProductSku("SKU");
        dto2.setWarehouseId(1L);

        assertThat(dto).isEqualTo(dto2);
        assertThat(dto).hasSameHashCodeAs(dto2);
        assertThat(dto.toString()).contains("SKU");
    }
}
