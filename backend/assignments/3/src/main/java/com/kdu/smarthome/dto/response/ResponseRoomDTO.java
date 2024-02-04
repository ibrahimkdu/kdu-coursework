package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.RoomModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseRoomDTO {
    String message;
    RoomModel room;
    HttpStatus httpStatus;
}
