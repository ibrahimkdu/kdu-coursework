package com.kdu.Assesment2.controller;

import com.kdu.Assesment2.model.Inventory;
import com.kdu.Assesment2.model.Order;
import com.kdu.Assesment2.service.InventoryService;
import com.kdu.Assesment2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService)
    {
        this.inventoryService=inventoryService;
    }

    @PostMapping("/inventory")
    public ResponseEntity<String> saveShift(@RequestBody Inventory order) {
        inventoryService.saveAddress(order);
        return new ResponseEntity<>("Inventory saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/inventory/{inventoryId}")
    public ResponseEntity<Inventory> getShiftById(@PathVariable Integer inventoryId) {
        Inventory retrievedShift = inventoryService.getUserById(inventoryId);
        if (retrievedShift != null) {
            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getAllUsers() {
        List<Inventory> allShifts = inventoryService.getAllAddress();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
}
