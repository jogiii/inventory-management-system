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

        assertThat(dto)
                .returns(1L, CategoryDTO::getId)
                .returns("Name", CategoryDTO::getName)
                .returns("Desc", CategoryDTO::getDescription);

        CategoryDTO dto2 = new CategoryDTO();
        dto2.setId(1L);
        dto2.setName("Name");
        dto2.setDescription("Desc");

        assertThat(dto)
                .isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);
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

        assertThat(dto)
                .returns(1L, ProductDTO::getId)
                .returns("Name", ProductDTO::getName)
                .returns("SKU", ProductDTO::getSku)
                .returns("Desc", ProductDTO::getDescription)
                .returns(BigDecimal.TEN, ProductDTO::getPrice)
                .returns(5, ProductDTO::getLowStockThreshold)
                .returns(2L, ProductDTO::getCategoryId);

        ProductDTO dto2 = new ProductDTO();
        dto2.setId(1L);
        dto2.setName("Name");
        dto2.setSku("SKU");
        dto2.setDescription("Desc");
        dto2.setPrice(BigDecimal.TEN);
        dto2.setLowStockThreshold(5);
        dto2.setCategoryId(2L);

        assertThat(dto)
                .isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);
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

        assertThat(dto)
                .returns(1L, StockOperationDTO::getProductId)
                .returns(2L, StockOperationDTO::getWarehouseId)
                .returns(10, StockOperationDTO::getQuantity)
                .returns(StockOperationDTO.OperationType.IN, StockOperationDTO::getType)
                .returns("Reason", StockOperationDTO::getReason);

        StockOperationDTO dto2 = new StockOperationDTO();
        dto2.setProductId(1L);
        dto2.setWarehouseId(2L);
        dto2.setQuantity(10);
        dto2.setType(StockOperationDTO.OperationType.IN);
        dto2.setReason("Reason");

        assertThat(dto)
                .isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);
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

        assertThat(dto)
                .returns(1L, StockDTO::getId)
                .returns(100, StockDTO::getQuantity)
                .returns(90, StockDTO::getAvailableQuantity)
                .returns(1L, StockDTO::getProductId)
                .returns("SKU", StockDTO::getProductSku)
                .returns(1L, StockDTO::getWarehouseId);

        StockDTO dto2 = new StockDTO();
        dto2.setId(1L);
        dto2.setQuantity(100);
        dto2.setAvailableQuantity(90);
        dto2.setProductId(1L);
        dto2.setProductSku("SKU");
        dto2.setWarehouseId(1L);

        assertThat(dto)
                .isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);
        assertThat(dto.toString()).contains("SKU");
    }
}
