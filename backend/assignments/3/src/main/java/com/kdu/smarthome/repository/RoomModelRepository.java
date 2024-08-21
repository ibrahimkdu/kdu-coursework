package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.RoomModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomModelRepository extends CrudRepository<RoomModel, Integer> {

    @Query("SELECT r FROM RoomModel r WHERE r.id = :id AND r.houseModel.id = :houseID")
    RoomModel findIdAndHouseId(@Param("id") Integer roomID, @Param("houseID") Integer houseID);
}
