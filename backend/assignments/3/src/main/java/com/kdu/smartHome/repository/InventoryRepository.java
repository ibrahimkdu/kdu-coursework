package com.kdu.smartHome.repository;

import com.kdu.smartHome.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    // You can add more custom queries if needed
}