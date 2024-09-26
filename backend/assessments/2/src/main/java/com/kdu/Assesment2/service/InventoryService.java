package com.kdu.Assesment2.service;

import com.kdu.Assesment2.model.Address;
import com.kdu.Assesment2.model.Inventory;
import com.kdu.Assesment2.repository.AddressRepository;
import com.kdu.Assesment2.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository)
    {
        this.inventoryRepository=inventoryRepository;
    }
    public void saveAddress(Inventory inventory)
    {
        inventoryRepository.save(inventory);
    }


    public Inventory getUserById(Integer addressId) {
        return inventoryRepository.findById(addressId).orElse(null);
    }

    public List<Inventory> getAllAddress() {
        return inventoryRepository.findAll();
    }
}
