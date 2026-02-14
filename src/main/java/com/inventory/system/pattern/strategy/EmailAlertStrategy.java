package com.inventory.system.pattern.strategy;

import com.inventory.system.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("emailAlertStrategy")
public class EmailAlertStrategy implements AlertStrategy {
    @Override
    public void sendAlert(Product product, String message) {
        // In a real app, this would send an SMTP email
        log.info("SENDING EMAIL ALERT for Product {}: {}", product.getName(), message);
    }
}
