package com.kdu.Assesment2.controller;

import com.kdu.Assesment2.model.Users;
import com.kdu.Assesment2.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
//    private final ShiftService shiftService;
//
//    @Autowired
//    public ShiftController(ShiftService shiftService) {
//        this.shiftService = shiftService;
//    }
//
//    @PostMapping("/shift")
//    public ResponseEntity<String> saveShift(@RequestBody Shift shift) {
//        shiftService.saveShift(shift);
//        return new ResponseEntity<>("Shift saved successfully", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/shift/{shiftId}")
//    public ResponseEntity<Shift> getShiftById(@PathVariable UUID shiftId) {
//        Shift retrievedShift = shiftService.getShiftById(shiftId);
//        if (retrievedShift != null) {
//            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/shift")
//    public ResponseEntity<List<Shift>> getAllShifts() {
//        List<Shift> allShifts = shiftService.getAllShifts();
//        return new ResponseEntity<>(allShifts, HttpStatus.OK);
//    }

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService)
    {
        this.usersService=usersService;
    }

        @PostMapping("/users")
    public ResponseEntity<String> saveShift(@Valid @RequestBody Users users) {
        usersService.saveUser(users);
        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
    }
        @GetMapping("/users/{usersId}")
    public ResponseEntity<Users> getShiftById(@PathVariable Integer usersId) {
        Users retrievedShift = usersService.getUserById(usersId);
        if (retrievedShift != null) {
            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allShifts = usersService.getAllUsers();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
}
