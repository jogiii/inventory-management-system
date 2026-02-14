package com.inventory.system.config;

import com.inventory.system.entity.Warehouse;
import com.inventory.system.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (warehouseRepository.count() == 0) {
            Warehouse w = Warehouse.builder()
                    .name("Main Warehouse")
                    .location("Central City")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            warehouseRepository.save(w);
            System.out.println("PRE-LOADED: Main Warehouse (ID: 1)");
        }
    }
}
