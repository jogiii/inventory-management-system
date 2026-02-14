package com.inventory.system.mapper;

import com.inventory.system.dto.StockDTO;
import com.inventory.system.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.sku", target = "productSku")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    StockDTO toDTO(Stock stock);
}
