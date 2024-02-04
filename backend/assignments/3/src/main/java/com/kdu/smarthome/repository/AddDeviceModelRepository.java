/**
 * This is the data access object (DAO) interface for managing AddDeviceModel entities.
 * It extends the CrudRepository interface provided by Spring Data JPA.
 */
package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.AddDeviceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddDeviceModelRepository extends CrudRepository<AddDeviceModel,Integer> {


    @Query("SELECT dev.houseModel, dev.deviceModel, dev.roomModel FROM AddDeviceModel dev WHERE dev.houseModel.id = :houseId")
    List<Object[]> findHouseDeviceRoomByHouseId(@Param("houseId") Integer houseId);
    @Query("SELECT dev FROM AddDeviceModel dev WHERE dev.deviceModel.kickstonId = :kickston_id")
    AddDeviceModel findByKickStonId(@Param("kickston_id") String kickstonId);

}
