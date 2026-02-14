package com.inventory.system.mapper;

import com.inventory.system.dto.StockDTO;
import com.inventory.system.entity.Product;
import com.inventory.system.entity.Stock;
import com.inventory.system.entity.Warehouse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-14T17:12:11+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.4.jar, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public StockDTO toDTO(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDTO stockDTO = new StockDTO();

        stockDTO.setProductId( stockProductId( stock ) );
        stockDTO.setProductSku( stockProductSku( stock ) );
        stockDTO.setWarehouseId( stockWarehouseId( stock ) );
        stockDTO.setId( stock.getId() );
        stockDTO.setQuantity( stock.getQuantity() );
        stockDTO.setAvailableQuantity( stock.getAvailableQuantity() );

        return stockDTO;
    }

    private Long stockProductId(Stock stock) {
        if ( stock == null ) {
            return null;
        }
        Product product = stock.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String stockProductSku(Stock stock) {
        if ( stock == null ) {
            return null;
        }
        Product product = stock.getProduct();
        if ( product == null ) {
            return null;
        }
        String sku = product.getSku();
        if ( sku == null ) {
            return null;
        }
        return sku;
    }

    private Long stockWarehouseId(Stock stock) {
        if ( stock == null ) {
            return null;
        }
        Warehouse warehouse = stock.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        Long id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
