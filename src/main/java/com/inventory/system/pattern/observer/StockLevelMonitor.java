package com.inventory.system.pattern.observer;

import com.inventory.system.entity.Stock;
import com.inventory.system.pattern.strategy.AlertStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockLevelMonitor implements StockObserver {

    private final AlertStrategy alertStrategy;

    // Using constructor injection with @Qualifier to choose strategy
    public StockLevelMonitor(@Qualifier("emailAlertStrategy") AlertStrategy alertStrategy) {
        this.alertStrategy = alertStrategy;
    }

    @Override
    public void onStockChange(Stock stock) {
        if (stock.getQuantity() <= stock.getProduct().getLowStockThreshold()) {
            String message = String.format("Current stock: %d. Threshold: %d",
                    stock.getQuantity(), stock.getProduct().getLowStockThreshold());
            alertStrategy.sendAlert(stock.getProduct(), message);
        }
    }
}
