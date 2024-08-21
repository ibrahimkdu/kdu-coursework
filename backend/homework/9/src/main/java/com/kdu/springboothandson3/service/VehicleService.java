package com.kdu.springboothandson3.service;

import com.kdu.springboothandson3.dto.VehicleRequestDto;
import com.kdu.springboothandson3.dto.VehicleResponseDto;
import com.kdu.springboothandson3.mapper.VehicleMapping;
import com.kdu.springboothandson3.repository.Inventory;
import org.springframework.stereotype.Service;



@Service
public class VehicleService implements VehicleServiceInterface {
    private final Inventory inventory = new Inventory();

    @Override
    public void createVehicle(VehicleRequestDto vehicleDto) {
        inventory.save(VehicleMapping.vehicleRequestDtoToVehicle(vehicleDto));
    }

    @Override
    public VehicleResponseDto getVehicleById(int id) {
        return VehicleMapping.vehicleToResponseDto(inventory.findById(id));
    }



    @Override
    public void updateVehicle(int id, VehicleRequestDto vehicleDto) {
        inventory.updateVehicle(id, VehicleMapping.vehicleRequestDtoToVehicle(vehicleDto));
    }

    @Override
    public void deleteVehicleById(int id) {
        inventory.delete(id);
    }

    @Override
    public VehicleResponseDto mostExpensive() {
        return inventory.mostExpensive();
    }

    @Override
    public VehicleResponseDto leastExpensive() {
        return inventory.leastExpensive();
    }
}
