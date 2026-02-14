package com.inventory.system.mapper;

import com.inventory.system.dto.ProductDTO;
import com.inventory.system.entity.Category;
import com.inventory.system.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-14T17:12:11+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.4.jar, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setCategoryId( productCategoryId( product ) );
        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setSku( product.getSku() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setLowStockThreshold( product.getLowStockThreshold() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder<?, ?> product = Product.builder();

        product.category( productDTOToCategory( dto ) );
        product.id( dto.getId() );
        product.name( dto.getName() );
        product.sku( dto.getSku() );
        product.description( dto.getDescription() );
        product.price( dto.getPrice() );
        product.lowStockThreshold( dto.getLowStockThreshold() );

        return product.build();
    }

    private Long productCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Category productDTOToCategory(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.id( productDTO.getCategoryId() );

        return category.build();
    }
}
