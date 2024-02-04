package com.kdu.smarthome.utils;

import com.kdu.smarthome.dto.request.RequestRoomDTO;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.model.RoomModel;


public class RoomMapping {

    public static RoomModel dtomodelmapper(RequestRoomDTO requestRoomDTO, HouseModel houseModel) {
        RoomModel room = new RoomModel();
        HouseModel house = houseModel;
        house.setHouseName(house.getHouseName());
        room.setRoomName(requestRoomDTO.getRoom_name());
        room.setHouseModel(house);
        return room;
    }
}
