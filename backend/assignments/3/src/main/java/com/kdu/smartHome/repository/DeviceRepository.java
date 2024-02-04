package com.kdu.smartHome.repository;

import com.kdu.smartHome.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findByHouseId(Integer houseId);
    Device findByKickstonId(String kickstonId);
    // You can add more custom queries if needed
}