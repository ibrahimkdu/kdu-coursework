package com.kdu.smarthome.service;

import com.kdu.smarthome.repository.AddDeviceModelRepository;
import com.kdu.smarthome.repository.RegisteredDeviceRepository;
import com.kdu.smarthome.dto.request.RequestAddDeviceDTO;
import com.kdu.smarthome.dto.request.RequestDeviceRegisterDTO;
import com.kdu.smarthome.dto.request.RequestUserDTO;
import com.kdu.smarthome.enums.HouseRole;
import com.kdu.smarthome.exception.ResourceNotFoundException;
import com.kdu.smarthome.utils.DeviceMapping;
import com.kdu.smarthome.utils.HouseMapping;
import com.kdu.smarthome.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;
import java.util.regex.Pattern;
@Service
@Slf4j
public class DeviceService {

    private RegisteredDeviceRepository registeredDeviceRepository;
    private final UserService userService;
    private final InventoryService inventoryService;
    private final HouseService houseService;
    private final RoomService roomService;

    private final AddDeviceModelRepository addDeviceModelRepository;
    @Autowired
    public DeviceService(RegisteredDeviceRepository registeredDeviceRepository, UserService userService, InventoryService inventoryService,
                         HouseService houseService, RoomService roomService, AddDeviceModelRepository addDeviceModelRepository) {
        this.registeredDeviceRepository = registeredDeviceRepository;
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.houseService = houseService;
        this.roomService = roomService;
        this.addDeviceModelRepository = addDeviceModelRepository;
    }
    public RegisteredDevice register(RequestDeviceRegisterDTO requestDeviceRegisterDTO) throws CredentialException {
        try {
            String currentUser = HouseMapping.currentUserName();
            RequestUserDTO user = userService.getUserByUsername(currentUser);
            DeviceModel deviceModel = inventoryService.getDeviceByInventory(requestDeviceRegisterDTO.getKickston_id());
            if (user != null && deviceModel != null) {
                if (!deviceModel.getDeviceUsername().equals(requestDeviceRegisterDTO.getDevice_username())) {
                    throw new ValidationException("Username or device not found");
                }
                if (deviceModel.getDevicePassword().equals(requestDeviceRegisterDTO.getDevice_password())) {
                    RegisteredDevice registeredDevice = DeviceMapping.toRegisteredDevice();
                    registeredDevice.setRequestUserDTO(user);
                    registeredDevice.setDeviceModel(deviceModel);
                    registeredDeviceRepository.save(registeredDevice);
                    return registeredDevice;
                } else {
                    throw new CredentialException("Invalid credentials");
                }
            } else {
                throw new ValidationException("Username or device not found");
            }
        } catch (ValidationException | CredentialException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred");
        }
    }
    public DeviceModel addDevice(RequestAddDeviceDTO addDeviceRequest) throws Exception {
        AddDeviceModel addDeviceModel = DeviceMapping.toAddDeviceModel();
        String id = addDeviceRequest.getHouseId();
        Pattern pattern = Pattern.compile("^\\d+$");
        if(id==null ||!pattern.matcher(id).matches())
        {
            throw new ResourceNotFoundException("House ID not found");
        }
        int idInteger = Integer.parseInt(id);
        String roomId = addDeviceRequest.getRoomId();
        if(roomId==null ||!pattern.matcher(roomId).matches())
        {
            throw new ResourceNotFoundException("Room ID not found");
        }
        int roomIdInteger = Integer.parseInt(roomId);
        String kickstonId = addDeviceRequest.getKickstonId();
        RegisteredDevice registeredDevice = registeredDeviceRepository.findByKickstonId(kickstonId);
        AddDeviceModel deviceModel1= addDeviceModelRepository.findByKickStonId(kickstonId);
        HouseModel houseModel = houseService.getHouseById(idInteger);
        if (houseModel == null) {
            throw new ResourceNotFoundException("House ID not found");
        }
        RoomModel roomModel = roomService.getRoomByIdAndHouseId(roomIdInteger, houseModel.getId());
        if (roomModel == null) {
            throw new ResourceNotFoundException("Room ID not found");
        }
        if (registeredDevice == null) {
            throw new ResourceNotFoundException("Device ID not found");
        }
        String currentUser = HouseMapping.currentUserName();
        HouseUser houseUser = houseService.houseUserRepository.findAllByUserUsernameAndHouseId(currentUser, houseModel.getId());
        if (houseUser == null) {
            throw new RuntimeException("User not registered under this house");
        }
        DeviceModel deviceModel = inventoryService.getDeviceByInventory(kickstonId);
        if(deviceModel1!=null && deviceModel1.getHouseModel().getId()==idInteger){
           RoomModel roomModel1 = roomService.getRoomByIdAndHouseId(deviceModel1.getRoomModel().getId(),idInteger);
            deviceModel1.setRoomModel(roomModel1);
            addDeviceModelRepository.save(deviceModel1);
            return  deviceModel1.getDeviceModel();
        }
      else if(deviceModel1==null) {
            addDeviceModel.setHouseModel(houseModel);
            addDeviceModel.setRoomModel(roomModel);
            addDeviceModel.setDeviceModel(deviceModel);
            if (houseUser.getHouseRole().equals(HouseRole.ADMIN))
                addDeviceModel.setHouseUser(houseUser);
            else {
                throw new RuntimeException("user not admin");
            }
            addDeviceModelRepository.save(addDeviceModel);
            return deviceModel;
        }
      else {
          throw new RuntimeException("Unable to move or add device");
        }
    }
}
