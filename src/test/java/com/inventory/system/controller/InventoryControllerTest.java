package com.inventory.system.controller;

import com.inventory.system.dto.StockOperationDTO;
import com.inventory.system.pattern.facade.InventoryFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryFacade inventoryFacade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void operateStock_ShouldReturnOk() throws Exception {
        StockOperationDTO dto = new StockOperationDTO();
        dto.setProductId(1L);
        dto.setWarehouseId(1L);
        dto.setQuantity(10);
        dto.setType(StockOperationDTO.OperationType.IN);

        doNothing().when(inventoryFacade).processStockOperation(dto);

        mockMvc.perform(post("/api/inventory/operate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Stock operation processed successfully"));
    }
}
