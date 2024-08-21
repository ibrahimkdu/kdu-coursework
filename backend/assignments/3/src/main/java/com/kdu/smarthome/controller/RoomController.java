package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RequestRoomDTO;
import com.kdu.smarthome.dto.response.ResponseRoomDTO;
import com.kdu.smarthome.model.RoomModel;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<ResponseRoomDTO> addRoomToHouse(
            @RequestParam("houseId") Integer houseId,
            @RequestBody RequestRoomDTO requestRoomDTO) {

        if (houseId == null) {
            ResponseRoomDTO response = new ResponseRoomDTO("Error: HouseId cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (requestRoomDTO == null || requestRoomDTO.getRoom_name() == null || requestRoomDTO.getRoom_name().isEmpty()) {
            ResponseRoomDTO response = new ResponseRoomDTO("Error: Room name cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            RoomModel roomModel = roomService.addRoom(requestRoomDTO, houseId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseRoomDTO(" Room added successfully", roomModel, HttpStatus.OK));
        } catch (Exception exc) {
            if (exc.getMessage().equals("Current user isn't admin")) {
                ResponseRoomDTO response = new ResponseRoomDTO("Error: Current user isn't admin", null, HttpStatus.UNAUTHORIZED);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                ResponseRoomDTO response = new ResponseRoomDTO("Error: Failed to add room to the house: ", null, HttpStatus.INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }
}
