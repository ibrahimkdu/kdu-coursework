package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RequestHouseDTO;
import com.kdu.smarthome.dto.request.RequestUserNameDTO;
import com.kdu.smarthome.dto.response.ResponseCustomDTO;
import com.kdu.smarthome.dto.response.ResponseHousesDTO;
import com.kdu.smarthome.dto.response.ResponseHouseDTO;
import com.kdu.smarthome.dto.response.ResponseRoomsAndDevicesDTO;
import com.kdu.smarthome.model.HouseModel;
import com.kdu.smarthome.enums.HouseRole;
import com.kdu.smarthome.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }


    @PutMapping("/")
    public ResponseEntity<ResponseCustomDTO> updateHouseAddress(
            @RequestParam Integer houseId,
            @RequestBody Map<String, String> requestBody) {

        String newAddress = requestBody.get("address");

        if (newAddress == null || newAddress.isEmpty()) {
            ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Error: address cannot be empty !", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCustomDTO);
        }

        try {
            HouseModel updatedHouse = houseService.updateHouseAddress(houseId, newAddress);
            ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Address of house is updated successfully", updatedHouse, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseCustomDTO);
        } catch (Exception exc) {
            ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Failed to update address of the  house  ", null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCustomDTO);
        }
    }

    @PostMapping
    public HttpEntity<ResponseHouseDTO> addHouse(@RequestBody RequestHouseDTO requestHouseDTO) {
        try {
            log.info("HOUSE DTO RECEIVED:" + requestHouseDTO);
            HouseModel houseModel = houseService.addHouse(requestHouseDTO, HouseRole.ADMIN);
            ResponseHouseDTO responseVal = new ResponseHouseDTO("House added!", houseModel, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseVal);
        } catch (Exception exc) {
            ResponseHouseDTO responseVal = new ResponseHouseDTO("Unable to Add House " , null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVal);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ResponseHousesDTO> getAllHouses() {
        try {
            List<HouseModel> houseModelList = houseService.getAllHouses();
            ResponseHousesDTO genericResponse = new ResponseHousesDTO("Houses retrieved! ", houseModelList.toString(), HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
        } catch (Exception exc) {
            ResponseHousesDTO genericResponse = new ResponseHousesDTO("Failed to get houses: ", null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
        }
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<ResponseCustomDTO> addUserToHouse(
            @PathVariable("houseId") Integer houseId,
            @RequestBody RequestUserNameDTO username) {

        if (username == null || username.getUsername() == null || username.getUsername().isEmpty()) {
            ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Error:Username cannot be empty", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCustomDTO);
        }
        try {
            String userName = houseService.addUserToHouse(houseId, username.getUsername());
            ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("User is Successfully  ", userName, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseCustomDTO);
        } catch (UsernameNotFoundException exc) {
            ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Error: Username not found", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseCustomDTO);

        } catch (Exception exc) {
            if (exc.getMessage().equals("User not admin")) {
                ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Error: User is not an admin", null, HttpStatus.UNAUTHORIZED);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseCustomDTO);
            } else {
                ResponseCustomDTO responseCustomDTO = new ResponseCustomDTO("Error: Failed to add user to the house: " , null, HttpStatus.INTERNAL_SERVER_ERROR);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseCustomDTO);
            }
        }
    }

    @GetMapping("/{houseId}")
    public HttpEntity<ResponseRoomsAndDevicesDTO> getAllDevicesAndRooms(@PathVariable String houseId) {
        try {
            Integer houseIDInt = Integer.parseInt(houseId);
            String addDeviceModel = houseService.getAllDetails(houseIDInt);
            ResponseRoomsAndDevicesDTO responseRoomsAndDevicesDTO = new ResponseRoomsAndDevicesDTO("All devices abd Rooms are Successfully retrieved", addDeviceModel, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(responseRoomsAndDevicesDTO);

        } catch (Exception e) {
            ResponseRoomsAndDevicesDTO responseRoomsAndDevicesDTO = new ResponseRoomsAndDevicesDTO("Error:Failed to get rooms and devices to the house: " , null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseRoomsAndDevicesDTO);
        }
    }
}
