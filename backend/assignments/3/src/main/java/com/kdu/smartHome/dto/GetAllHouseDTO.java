package com.kdu.smartHome.dto;

import com.kdu.smartHome.model.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;



import com.kdu.smartHome.model.House;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class GetAllHouseDTO {

    private final String message;
    private final List<House> houses;
    private final HttpStatus httpStatus;

    public GetAllHouseDTO(String message, List<House> houses, HttpStatus httpStatus) {
        this.message = message;
        this.houses = houses;
        this.httpStatus = httpStatus;
    }

    // Getters
}