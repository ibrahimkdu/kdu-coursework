package com.kdu.smarthome.service;

import com.kdu.smarthome.repository.HouseUserRepository;
import com.kdu.smarthome.repository.RoomModelRepository;
import com.kdu.smarthome.dto.request.RequestRoomDTO;
import com.kdu.smarthome.utils.HouseMapping;
import com.kdu.smarthome.utils.RoomMapping;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.model.HouseUser;
import com.kdu.smarthome.model.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    HouseService houseService;
    RoomModelRepository roomModelRepository;
    HouseUserRepository houseDAO;
    @Autowired
    RoomService(HouseService houseService, RoomModelRepository roomModelRepository, HouseUserRepository houseUser) {
        this.houseService = houseService;
        this.roomModelRepository = roomModelRepository;
        this.houseDAO = houseUser;
    }
    public RoomModel addRoom(RequestRoomDTO room, Integer houseID) throws Exception {
        HouseModel houseModel = houseService.getHouseById(houseID);
        Optional<HouseUser> houseUserPre = houseDAO.findById(houseID);

        if (houseUserPre.isPresent()) {
            HouseUser houseUser = houseUserPre.get();
            if (HouseMapping.currentUserName().equals(houseUser.getUser().getUsername())) {
                RoomModel roomModel = RoomMapping.dtomodelmapper(room, houseModel);
                roomModelRepository.save(roomModel);
                return roomModel;
            } else {
                throw new RuntimeException(" user is not an admin");
            }
        } else {
            throw new RuntimeException("house id not found");
        }
    }
    public RoomModel getRoomById(int roomId) {
        Optional<RoomModel> roomModel = roomModelRepository.findById(roomId);
        return roomModel.orElse(null);
    }
    public RoomModel getRoomByIdAndHouseId(int roomId, int houseId) {
        return roomModelRepository.findIdAndHouseId(roomId, houseId);
    }
}
