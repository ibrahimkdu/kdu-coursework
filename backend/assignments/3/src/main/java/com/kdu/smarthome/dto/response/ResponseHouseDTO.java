package com.kdu.smarthome.dto.response;

import com.kdu.smarthome.model.HouseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseHouseDTO {
    String message;
    HouseModel house;
    HttpStatus httpStatus;
}
