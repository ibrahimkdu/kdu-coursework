// InventoryController.java
package com.kdu.smartHome.controllers;

import com.kdu.smartHome.dto.InventoryRequestDTO;
import com.kdu.smartHome.dto.InventoryResponseDTO;
import com.kdu.smartHome.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<String> getInventory() {
        // Implement the logic to retrieve inventory items
        String inventory = inventoryService.getInventory();
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/inventory")
    public ResponseEntity<InventoryResponseDTO> addItemToInventory(
            @RequestBody InventoryRequestDTO inventoryRequest
    ) {
        InventoryResponseDTO response = inventoryService.addItemToInventory(inventoryRequest);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
