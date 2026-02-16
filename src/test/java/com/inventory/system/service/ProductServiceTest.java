package com.inventory.system.service;

import com.inventory.system.dto.ProductDTO;
import com.inventory.system.entity.Category;
import com.inventory.system.entity.Product;
import com.inventory.system.mapper.ProductMapper;
import com.inventory.system.repository.CategoryRepository;
import com.inventory.system.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct_ShouldReturnDTO_WhenCategoryExists() {
        ProductDTO dto = new ProductDTO();
        dto.setCategoryId(1L);
        dto.setPrice(BigDecimal.TEN);

        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = productService.createProduct(dto);

        assertThat(result).isNotNull();
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void createProduct_ShouldThrowException_WhenCategoryNotFound() {
        ProductDTO dto = new ProductDTO();
        dto.setCategoryId(999L);

        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.createProduct(dto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Category not found");
    }

    @Test
    void getAllProducts_ShouldReturnList() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(new Product()));
        when(productMapper.toDTO(any(Product.class))).thenReturn(new ProductDTO());

        List<ProductDTO> result = productService.getAllProducts();

        assertThat(result).hasSize(1);
    }

    @Test
    void getProduct_ShouldReturnDTO_WhenExists() {
        Product product = new Product();
        product.setId(1L);
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = productService.getProduct(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getProduct_ShouldThrowException_WhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getProduct(1L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createProduct_ShouldReturnDTO_WhenCategoryIsNull() {
        ProductDTO dto = new ProductDTO();
        dto.setCategoryId(null);
        dto.setPrice(BigDecimal.TEN);

        Product product = new Product();
        product.setId(1L);

        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = productService.createProduct(dto);

        assertThat(result).isNotNull();
        verify(categoryRepository, never()).findById(anyLong());
        verify(productRepository).save(any(Product.class));
    }
}
