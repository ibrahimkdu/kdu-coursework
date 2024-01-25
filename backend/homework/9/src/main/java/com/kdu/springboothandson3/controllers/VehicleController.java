package com.kdu.springboothandson3.controllers;

import com.kdu.springboothandson3.exceptions.BadRequestCustomException;
import com.kdu.springboothandson3.exceptions.ResourceNotFound;
import com.kdu.springboothandson3.dto.VehicleRequestDto;
import com.kdu.springboothandson3.dto.VehicleResponseDto;
import com.kdu.springboothandson3.service.VehicleServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    private final VehicleServiceInterface vehicleService;

    public VehicleController(VehicleServiceInterface vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicles")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleRequestDto vehicle) {
        vehicleService.createVehicle(vehicle);
        String created = "The vehicle is added ";
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/vehicles/{id}")
    public VehicleResponseDto getVehicle(@PathVariable String id) throws ResourceNotFound, BadRequestCustomException {
        try {
            int numericId = Integer.parseInt(id);
            return vehicleService.getVehicleById(numericId);
        } catch (NumberFormatException ex) {
            throw new BadRequestCustomException("Bad request Exception called");
        } catch (IndexOutOfBoundsException ex) {
            throw new ResourceNotFound("NO vehicle stored at id " + id);
        }
    }



    @PutMapping("/vehicles/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id, @RequestBody VehicleRequestDto vehicle) throws BadRequestCustomException {

        vehicleService.updateVehicle(id, vehicle);
        String updated = "The vehicle is Updated";
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicleById(id);
        String deleted = "The vehicle is deleted";
        return new ResponseEntity<>(deleted, HttpStatus.CREATED);
    }

    @GetMapping("/vehicles/mostExpensive")
    public VehicleResponseDto mostExpensive() {
        return vehicleService.mostExpensive();
    }

    @GetMapping("/vehicles/leastExpensive")
    public VehicleResponseDto leastExpensive() {
        return vehicleService.leastExpensive();
    }
}
