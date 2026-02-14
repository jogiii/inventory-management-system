package com.inventory.system.pattern.observer;

import com.inventory.system.entity.Stock;

public interface StockObserver {
    void onStockChange(Stock stock);
}
