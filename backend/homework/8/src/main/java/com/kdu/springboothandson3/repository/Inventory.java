package com.kdu.springboothandson3.repository;

import com.kdu.springboothandson3.dto.VehicleResponseDto;
import com.kdu.springboothandson3.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private List<Vehicle> vehicles = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(Inventory.class);

    public void save(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        logger.info("Vehicle saved: {}", vehicle);
    }

    public Vehicle findById(int id) {
        logger.info("Finding vehicle by id: {}", id);
        return vehicles.get(id);
    }

    public void updateVehicle(int id, Vehicle vehicle) {
        vehicles.set(id, vehicle);
        logger.info("Vehicle updated: {}", vehicle);
    }

    public void delete(int id) {
        vehicles.remove(id);
        logger.info("Vehicle deleted with id: {}", id);
    }

    public VehicleResponseDto mostExpensive() {
        Vehicle mostExpensive = vehicles.stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
        return mostExpensive != null ? new VehicleResponseDto(mostExpensive.getName(), mostExpensive.getPrice()) : null;
    }

    public VehicleResponseDto leastExpensive() {
        Vehicle leastExpensive = vehicles.stream()
                .min((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
        return leastExpensive != null ? new VehicleResponseDto(leastExpensive.getName(), leastExpensive.getPrice()) : null;
    }

    public List<VehicleResponseDto> getVehicles() {
        return vehicles.stream()
                .map(v -> new VehicleResponseDto(v.getName(), v.getPrice()))
                .collect(Collectors.toList());
    }
}
