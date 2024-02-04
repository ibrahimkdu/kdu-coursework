package com.kdu.smartHome.repository;

import com.kdu.smartHome.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByHouseId(Integer houseId);
    // You can add more custom queries if needed
}