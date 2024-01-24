package com.kdu.springboothandson3.service;

import com.kdu.springboothandson3.dto.VehicleRequestDto;
import com.kdu.springboothandson3.dto.VehicleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleServiceInterface {
    void createVehicle(VehicleRequestDto vehicleDto);
    VehicleResponseDto getVehicleById(int id);
    void updateVehicle(int id, VehicleRequestDto vehicle);
    void deleteVehicleById(int id);
    VehicleResponseDto mostExpensive();
    VehicleResponseDto leastExpensive();
}
