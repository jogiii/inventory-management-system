package com.inventory.system.pattern.strategy;

import com.inventory.system.entity.Product;

public interface AlertStrategy {
    void sendAlert(Product product, String message);
}
