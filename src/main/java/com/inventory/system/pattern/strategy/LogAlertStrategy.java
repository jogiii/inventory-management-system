package com.inventory.system.pattern.strategy;

import com.inventory.system.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("logAlertStrategy")
public class LogAlertStrategy implements AlertStrategy {
    @Override
    public void sendAlert(Product product, String message) {
        log.warn("LOW STOCK LOG: Product {} - {}", product.getName(), message);
    }
}
