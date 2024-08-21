package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.RegisteredDevice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RegisteredDeviceRepository extends CrudRepository<RegisteredDevice,Integer> {
    @Query("SELECT dev FROM RegisteredDevice dev WHERE dev.deviceModel.kickstonId = :kickstonId")
    RegisteredDevice findByKickstonId(String kickstonId);
}
