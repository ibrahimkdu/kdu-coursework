package com.kdu.springboothandson3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleRequestDto {
    String name;
    double price;
}
