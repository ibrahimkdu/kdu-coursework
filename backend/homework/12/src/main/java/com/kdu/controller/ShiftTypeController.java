package com.kdu.controller;

import com.kdu.model.ShiftType;
import com.kdu.service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/app/v1/")
public class ShiftTypeController {
    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }
    @PostMapping("/shifsType")
    public ResponseEntity<String> saveShiftsType(@RequestBody ShiftType shiftType) {
        shiftTypeService.save(shiftType);
        return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/shiftsType/{shiftsTypeId}")
    public ResponseEntity<ShiftType> getShifstTypeById(@PathVariable UUID shiftTypeId) {
        Optional<ShiftType> retrievedShiftType = shiftTypeService.get(shiftTypeId);
        if (retrievedShiftType.isPresent()) {
            return new ResponseEntity<>(retrievedShiftType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/shiftsType")
    public ResponseEntity<List<ShiftType>> getAllShifts() {
        List<ShiftType> allShiftTypes = shiftTypeService.getAll();
        return new ResponseEntity<>(allShiftTypes, HttpStatus.OK);
    }

}
