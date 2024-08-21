package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RequestDeviceDTO;
import com.kdu.smarthome.dto.response.ResponseCustomDTO;
import com.kdu.smarthome.dto.response.ResponseInventoryDTO;
import com.kdu.smarthome.model.DeviceModel;
import com.kdu.smarthome.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

@RestController
@Slf4j
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomDTO> addInventory(@RequestBody RequestDeviceDTO requestDeviceDTO) {
        try {
            DeviceModel deviceModel = inventoryService.add(requestDeviceDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseCustomDTO("Inventory added successfully", deviceModel, HttpStatus.OK));
        } catch (ValidationException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseCustomDTO("Error: Failed to add Inventory ", null, HttpStatus.BAD_REQUEST));
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseCustomDTO("Unexpected error: " , null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @GetMapping
    public ResponseEntity<ResponseInventoryDTO> displayInventory() {
        try {
            String inventoryList = inventoryService.getAllDevices();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInventoryDTO("Inventory retrieved successfully", inventoryList, HttpStatus.OK));
        } catch (Exception e) {
            log.error("Failed to retrieve inventory: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseInventoryDTO("Failed to retrieve inventory: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
