package com.kdu.smartHome.service;

import com.kdu.smartHome.dto.DeviceRequestDTO;
import com.kdu.smartHome.dto.DeviceResponseDTO;
import com.kdu.smartHome.model.Device;
import com.kdu.smartHome.model.House;
import com.kdu.smartHome.model.Room;
import com.kdu.smartHome.repository.DeviceRepository;
import com.kdu.smartHome.repository.HouseRepository;
import com.kdu.smartHome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.kdu.smartHome.model.Device;
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, HouseRepository houseRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    public ResponseEntity<DeviceResponseDTO> registerDevice(DeviceRequestDTO deviceRequest) {
        Device newDevice = new Device();
        newDevice.setKickstonId(deviceRequest.getKickstonId());
        newDevice.setDeviceName(deviceRequest.getDeviceUsername());
        newDevice.setDevicePassword(deviceRequest.getDevicePassword());

        Device savedDevice = deviceRepository.save(newDevice);

        return ResponseEntity.ok(new DeviceResponseDTO("Device registered successfully", savedDevice.getId()));
    }

    public ResponseEntity<DeviceResponseDTO> addDeviceToHouse(String houseId, String roomId, String kickstonId) {
        // Fetch house and room by their IDs
        House house = houseRepository.findById(Integer.parseInt(houseId))
                .orElseThrow(() -> new RuntimeException("House not found"));

        Room room = roomRepository.findById(Integer.parseInt(roomId))
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Fetch device by kickstonId
        Device device = deviceRepository.findByKickstonId(kickstonId);

        // Associate the device with the house and room
        device.setHouse(house);
        device.setRoom(room);

        deviceRepository.save(device);

        return ResponseEntity.ok(new DeviceResponseDTO("Device added to house successfully", device.getId()));
    }
}
