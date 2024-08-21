package com.kdu.smarthome.utils;

import com.kdu.smarthome.dto.request.RequestDeviceDTO;
import com.kdu.smarthome.model.AddDeviceModel;
import com.kdu.smarthome.model.DeviceModel;
import com.kdu.smarthome.model.RegisteredDevice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeviceMapping {

    public static DeviceModel toDeviceModel(RequestDeviceDTO requestDeviceDTO) {
        DeviceModel device = new DeviceModel();
        device.setDeviceUsername(requestDeviceDTO.getDevice_username());
        device.setKickstonId(requestDeviceDTO.getKickston_id());
        device.setDevicePassword(requestDeviceDTO.getDevice_password());
        LocalDateTime manufactureDateTime = LocalDateTime.parse(
                requestDeviceDTO.getManufacture_date_time(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
        );
        device.setManufactureDateTime(manufactureDateTime);
        device.setManufactureFactoryPlace(requestDeviceDTO.getManufacture_factory_place());
        return device;
    }
    public static RegisteredDevice toRegisteredDevice() {
        return new RegisteredDevice();
    }
    public static AddDeviceModel toAddDeviceModel() {
        return new AddDeviceModel();
    }
}
