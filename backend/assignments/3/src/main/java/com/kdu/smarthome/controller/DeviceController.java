package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.RequestAddDeviceDTO;
import com.kdu.smarthome.dto.request.RequestDeviceRegisterDTO;
import com.kdu.smarthome.dto.response.ResponseCustomDTO;
import com.kdu.smarthome.model.DeviceModel;
import com.kdu.smarthome.model.RegisteredDevice;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import javax.validation.ValidationException;


@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseCustomDTO> addDevice(@RequestBody RequestDeviceRegisterDTO requestDeviceRegisterDTO) {
        try {
            RegisteredDevice registeredDevice = deviceService.register(requestDeviceRegisterDTO);
            return ResponseEntity.ok(new ResponseCustomDTO("The device is  successfully registered ", registeredDevice, HttpStatus.OK));
        } catch (ValidationException exc) {
            return ResponseEntity.badRequest().body(new ResponseCustomDTO("Device Failed to register", null, HttpStatus.BAD_REQUEST));
        } catch (CredentialException exc) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseCustomDTO("Wrong creds", null, HttpStatus.UNAUTHORIZED));
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseCustomDTO("Unexpected error occurred: ", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseCustomDTO> addDeviceToHouse(@RequestBody RequestAddDeviceDTO addDeviceRequest) {
        try {
            DeviceModel deviceModel = deviceService.addDevice(addDeviceRequest);
            return ResponseEntity.ok(new ResponseCustomDTO("Device is added to house successfully", deviceModel, HttpStatus.OK));
        } catch (ValidationException exc) {
            return ResponseEntity.badRequest().body(new ResponseCustomDTO("device failed to add " , null, HttpStatus.BAD_REQUEST));
        } catch (RuntimeException exc) {
            HttpStatus status;
            if (exc.getMessage().contains("House ID not found") || exc.getMessage().contains("Room ID not found") || exc.getMessage().contains("Device ID not found")) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return ResponseEntity.status(status).body(new ResponseCustomDTO("Unexpected error:", null, status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseCustomDTO("Unexpected error: ", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
