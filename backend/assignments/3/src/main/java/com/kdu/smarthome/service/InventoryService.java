package com.kdu.smarthome.service;

import com.kdu.smarthome.repository.InventoryRepository;
import com.kdu.smarthome.dto.request.RequestDeviceDTO;
import com.kdu.smarthome.utils.DeviceMapping;
import com.kdu.smarthome.model.DeviceModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {
    InventoryRepository inventoryRepository;

    InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public DeviceModel add(RequestDeviceDTO requestDeviceDTO) {
        DeviceModel deviceModel = DeviceMapping.toDeviceModel(requestDeviceDTO);
        try {
            return inventoryRepository.save(deviceModel);
        } catch (Exception exc) {
            throw exc;
        }
    }
    public String getAllDevices() {
        try {
            return inventoryRepository.findAll().toString();
        } catch (Exception exc) {
            throw exc;
        }
    }
    public DeviceModel getDeviceByInventory(String kickstonId) {
        DeviceModel deviceModel = inventoryRepository.findByKickstonId(kickstonId);
        if (deviceModel == null) {
            throw new RuntimeException("Kickston Id not found");
        }
        return deviceModel;
    }
}
