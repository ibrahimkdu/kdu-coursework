package com.kdu.smarthome.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ResponseRoomsAndDevicesDTO {
    String message;
    String roomsAndDevices;
    HttpStatus httpStatus;
}
