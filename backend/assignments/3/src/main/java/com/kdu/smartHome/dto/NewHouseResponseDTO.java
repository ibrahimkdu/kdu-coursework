package com.kdu.smartHome.dto;

import com.kdu.smartHome.model.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import com.kdu.smartHome.model.House;
import org.springframework.http.HttpStatus;

@Data
public class NewHouseResponseDTO {

    private final String message;
    private final House house;
    private final HttpStatus httpStatus;

    public NewHouseResponseDTO(String message, House house, HttpStatus httpStatus) {
        this.message = message;
        this.house = house;
        this.httpStatus = httpStatus;
    }

    // Getters
}