package com.inventory.system.pattern.command;

import com.inventory.system.entity.Stock;

public interface StockCommand {
    Stock execute();
}
