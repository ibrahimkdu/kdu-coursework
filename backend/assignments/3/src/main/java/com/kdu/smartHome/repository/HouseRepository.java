package com.kdu.smartHome.repository;

import com.kdu.smartHome.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {
    // You can add more custom queries if needed
}