package com.inventory.system.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StockTest {

    @Test
    void getAvailableQuantity_ShouldReturnCorrectDifference() {
        Stock stock = Stock.builder()
                .quantity(100)
                .reservedQuantity(20)
                .build();

        assertThat(stock.getAvailableQuantity()).isEqualTo(80);
    }

    @Test
    void getAvailableQuantity_ShouldReturnQuantity_WhenReservedIsZero() {
        Stock stock = Stock.builder()
                .quantity(50)
                .reservedQuantity(0)
                .build();

        assertThat(stock.getAvailableQuantity()).isEqualTo(50);
    }
}
