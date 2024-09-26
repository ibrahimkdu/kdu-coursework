package com.kdu.Assesment2.repository;

import com.kdu.Assesment2.model.Inventory;
import com.kdu.Assesment2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<Inventory, Integer> {


}