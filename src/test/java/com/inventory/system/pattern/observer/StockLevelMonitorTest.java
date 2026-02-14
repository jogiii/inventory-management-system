package com.inventory.system.pattern.observer;

import com.inventory.system.entity.Product;
import com.inventory.system.entity.Stock;
import com.inventory.system.pattern.strategy.AlertStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockLevelMonitorTest {

    @Mock
    private AlertStrategy alertStrategy;

    @InjectMocks
    private StockLevelMonitor monitor;

    @Test
    void onStockChange_ShouldAlert_WhenBelowThreshold() {
        Product product = new Product();
        product.setLowStockThreshold(10);
        product.setName("P1");

        Stock stock = new Stock();
        stock.setQuantity(5);
        stock.setProduct(product);

        // Re-inject mock manually as @InjectMocks might be tricky with constructor
        // sometimes,
        // but here simple instantiation is safer given the constructor change.
        monitor = new StockLevelMonitor(alertStrategy);

        monitor.onStockChange(stock);

        verify(alertStrategy).sendAlert(eq(product), anyString());
    }

    @Test
    void onStockChange_ShouldNotAlert_WhenAboveThreshold() {
        Product product = new Product();
        product.setLowStockThreshold(10);

        Stock stock = new Stock();
        stock.setQuantity(15);
        stock.setProduct(product);

        monitor = new StockLevelMonitor(alertStrategy);

        monitor.onStockChange(stock);

        verify(alertStrategy, never()).sendAlert(any(), anyString());
    }
}
