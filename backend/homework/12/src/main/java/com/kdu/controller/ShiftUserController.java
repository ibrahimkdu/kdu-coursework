package com.kdu.controller;
import com.kdu.model.ShiftUser;
import com.kdu.service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/app/v1/")
public class ShiftUserController {
    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }
    @PostMapping("/shiftuser")
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.save(shiftUser);
        return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{shiftUserId}")
    public ResponseEntity<ShiftUser> getShiftUserById(@PathVariable UUID shiftUserId) {
        Optional<ShiftUser> retrievedShiftUser = shiftUserService.get(shiftUserId);
        if (retrievedShiftUser.isPresent()) {
            return new ResponseEntity<>(retrievedShiftUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<ShiftUser>> getAllShiftUsers() {
        List<ShiftUser> allShiftUsers = shiftUserService.getAll();
        return new ResponseEntity<>(allShiftUsers, HttpStatus.OK);
    }

}