package com.kdu.smartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class HousesDevicesDTO {
    private String message;
    private List<RoomDTO> rooms;
    private List<DeviceDTO> devices;
    private HttpStatus httpStatus;
}
