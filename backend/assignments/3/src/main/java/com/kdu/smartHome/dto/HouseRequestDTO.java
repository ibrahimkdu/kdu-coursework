package com.kdu.smartHome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseRequestDTO {
    private String address;
    private String houseName;

    // Constructors, getters, setters
}
