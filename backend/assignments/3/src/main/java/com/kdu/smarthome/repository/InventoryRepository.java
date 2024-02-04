package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.DeviceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface InventoryRepository extends CrudRepository<DeviceModel,Integer> {

    @Query("SELECT dev FROM DeviceModel dev WHERE dev.kickstonId = :kickstonId")
    DeviceModel findByKickstonId(String kickstonId);

}
