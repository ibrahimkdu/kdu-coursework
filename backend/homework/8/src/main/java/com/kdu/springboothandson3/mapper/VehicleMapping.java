package com.kdu.springboothandson3.mapper;

import com.kdu.springboothandson3.dto.VehicleRequestDto;
import com.kdu.springboothandson3.dto.VehicleResponseDto;
import com.kdu.springboothandson3.model.Vehicle;

public class VehicleMapping {
    public static VehicleResponseDto vehicleToResponseDto(Vehicle vehicle) {
        return new VehicleResponseDto(vehicle.getName(), vehicle.getPrice());
    }

    public static Vehicle vehicleRequestDtoToVehicle(VehicleRequestDto vehicleDto) {
        return new Vehicle(vehicleDto.getName(), vehicleDto.getPrice());
    }
}
