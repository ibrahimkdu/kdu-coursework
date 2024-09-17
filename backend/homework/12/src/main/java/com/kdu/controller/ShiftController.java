package com.kdu.controller;

import com.kdu.model.Shift;
import com.kdu.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/app/v1/")
public class ShiftController {
    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping("/shifts")
    public ResponseEntity<String> saveShifts(@RequestBody Shift shift) {
        shiftService.save(shift);
        return new ResponseEntity<>(" saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/shifts/{shiftId}")
    public ResponseEntity<Shift> getShifstById(@PathVariable UUID shiftId) {
        Optional<Shift> retrievedShift = shiftService.get(shiftId);
        if (retrievedShift.isPresent()) {
            return new ResponseEntity<>(retrievedShift.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allShifts")
    public ResponseEntity<List<Shift>> getAllShifts() {
        List<Shift> allShifts = shiftService.getAll();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }

}