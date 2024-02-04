// RoomService.java
package com.kdu.smartHome.service;

import com.kdu.smartHome.dto.RoomRequestDTO;
import com.kdu.smartHome.dto.RoomResponseDTO;
import com.kdu.smartHome.model.House;
import com.kdu.smartHome.model.Room;
import com.kdu.smartHome.repository.HouseRepository;
import com.kdu.smartHome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
    }

    public RoomResponseDTO addRoomToHouse(String houseId, RoomRequestDTO roomRequest) {
        House house = houseRepository.findById(Integer.parseInt(houseId))
                .orElseThrow(() -> new RuntimeException("House not found"));

        Room newRoom = new Room();
        newRoom.setRoomName(roomRequest.getRoomName());
        newRoom.setHouse(house);

        Room savedRoom = roomRepository.save(newRoom);

        return new RoomResponseDTO("Room added successfully", savedRoom.getId());
    }
}
