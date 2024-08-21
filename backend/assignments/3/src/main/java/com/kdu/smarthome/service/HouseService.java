/**
 * Service class for managing operations related to houses in a smart home system.
 * This class includes methods for adding houses, adding users to houses, retrieving house details,
 * updating house information, and fetching a list of houses associated with the current user.
 */
package com.kdu.smarthome.service;

import com.kdu.smarthome.repository.AddDeviceModelRepository;
import com.kdu.smarthome.repository.HouseModelRepository;
import com.kdu.smarthome.repository.HouseUserRepository;
import com.kdu.smarthome.dto.request.RequestHouseDTO;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import com.kdu.smarthome.enums.HouseRole;
import com.kdu.smarthome.utils.HouseMapping;
import com.kdu.smarthome.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class HouseService {
    HouseModelRepository houseModelRepository;
    HouseUserRepository houseUserRepository;
    AddDeviceModelRepository addDeviceModelRepository;
    UserService userService;
    @Autowired
    HouseService(HouseModelRepository houseModelRepository, HouseUserRepository houseUserRepository, UserService userService, AddDeviceModelRepository addDeviceModelRepository) {
        this.houseModelRepository = houseModelRepository;
        this.houseUserRepository = houseUserRepository;
        this.userService = userService;
        this.addDeviceModelRepository = addDeviceModelRepository;
    }
    public HouseModel addHouse(RequestHouseDTO requestHouseDTO, HouseRole admin) throws Exception {
        try {
            HouseModel houseModel = HouseMapping.toHouseModel(requestHouseDTO, admin);
            houseModel = houseModelRepository.save(houseModel);
            HouseUser houseUser = HouseMapping.toHouseUser(requestHouseDTO, admin);
            RequestUserDTO requestUserDTO = userService.getUserByUsername(houseUser.getUser().getUsername());
            if (requestUserDTO == null) {
                throw new RuntimeException("UserDTO not found for username: " + houseUser.getUser().getUsername());
            }
            houseUser.setUser(requestUserDTO);
            houseUser.setHouse(houseModel);
            houseUserRepository.save(houseUser);
            return houseModel;
        } catch (Exception exc) {
            throw exc;
        }
    }
    public String addUserToHouse(Integer houseId, String newUser) throws Exception {
        try {
            String currentUser = HouseMapping.currentUserName();
            Optional<HouseUser> houseModel = houseUserRepository.findById(houseId);
            if (houseModel.isPresent()) {
                HouseUser house = houseModel.get();
                log.info(house.toString());
                if (house.getUser().getUsername().equals(currentUser)) {
                    RequestUserDTO requestUserDTO = userService.getUserByUsername(newUser);
                    log.info(requestUserDTO.toString());
                    HouseUser houseUser = new HouseUser();
                    houseUser.setUser(requestUserDTO);
                    houseUser.setHouseRole(HouseRole.BASIC);
                    houseUser.setHouse(house.getHouse());
                    houseUserRepository.save(houseUser);
                    return requestUserDTO.getName();
                } else {
                    throw new RuntimeException("User not admin");
                }
            }
            throw new RuntimeException("Unable to add user");
        } catch (Exception exc) {
            throw exc;
        }
    }
    public HouseModel getHouseById(Integer houseId) throws Exception {
        try {
            Optional<HouseModel> optionalHouseModel = houseModelRepository.findById(houseId);
            if (optionalHouseModel.isPresent()) {
                return optionalHouseModel.get();
            } else {
                throw new RuntimeException( "Invalid house id");
            }
        } catch (Exception exc) {
            throw exc;
        }
    }

    public HouseModel updateHouseAddress(Integer houseId, String newAddress) throws Exception {
        try {
            Optional<HouseModel> HouseModel = houseModelRepository.findById(houseId);
            if (HouseModel.isPresent()) {
                HouseModel houseModel = HouseModel.get();
                houseModel.setAddress(newAddress);
                return houseModelRepository.save(houseModel);
            } else {
                throw new RuntimeException("invalid house id");
            }
        } catch (Exception exc) {
            throw exc;
        }
    }
    public List<HouseModel> getAllHouses() {
        try {
            String currentUser = HouseMapping.currentUserName();
            List<HouseUser> houseUsers = houseUserRepository.findAllByUserUsername(currentUser);
            List<HouseModel> allHouses = new ArrayList<>();
            for (HouseUser houseUser : houseUsers) {
                Integer houseId = houseUser.getHouse().getId();
                HouseModel houseModel = houseModelRepository.findById(houseId).orElse(null);
                if (houseModel != null || houseModel.getAddress() == null) {
                    allHouses.add(houseModel);
                }
            }
            return allHouses;
        } catch (Exception exc) {
            throw exc;
        }
    }
    public String getAllDetails(Integer houseID) {
        List<Object[]> roomsAndDevices = addDeviceModelRepository.findHouseDeviceRoomByHouseId(houseID);
        StringBuilder allDetails = new StringBuilder();
        for (Object[] row : roomsAndDevices) {
            HouseModel houseModel = (HouseModel) row[0];
            DeviceModel deviceModel = (DeviceModel) row[1];
            RoomModel roomModel = (RoomModel) row[2];
            allDetails.append("House  ").append(houseModel.toString());
            allDetails.append("Device  ").append(deviceModel.toString());
            allDetails.append("Room  ").append(roomModel.toString());
        }
        return allDetails.toString();
    }
    public HouseUser findByHouseIDAndUsername(Integer houseID, String username)
    {
        return houseUserRepository.findAllByUserUsernameAndHouseId(username,houseID);
    }
}
