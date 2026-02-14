package com.inventory.system.controller;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.pattern.facade.InventoryFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryFacade inventoryFacade;

    @PostMapping("/operate")
    public ResponseEntity<String> operateStock(@RequestBody @Valid StockOperationDTO operationDTO) {
        inventoryFacade.processStockOperation(operationDTO);
        return ResponseEntity.ok("Stock operation processed successfully");
    }
}
