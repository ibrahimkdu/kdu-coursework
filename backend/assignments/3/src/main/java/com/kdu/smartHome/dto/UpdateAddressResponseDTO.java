package com.kdu.smartHome.dto;


import com.kdu.smartHome.model.House;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UpdateAddressResponseDTO {

    private final String message;
    private final House house;
    private final HttpStatus httpStatus;

    public UpdateAddressResponseDTO(String message, House house, HttpStatus httpStatus) {
        this.message = message;
        this.house = house;
        this.httpStatus = httpStatus;
    }

    // Getters
}