package com.kdu.Assesment2.controller;

import com.kdu.Assesment2.model.Address;

import com.kdu.Assesment2.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService)
    {
        this.addressService=addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<String> saveShift(@RequestBody Address address) {
        addressService.saveAddress(address);
        return new ResponseEntity<>("Address saved successfully", HttpStatus.CREATED);
    }
    @GetMapping("/address/{addressId}")
    public ResponseEntity<Address> getShiftById(@PathVariable Integer usersId) {
        Address retrievedShift = addressService.getUserById(usersId);
        if (retrievedShift != null) {
            return new ResponseEntity<>(retrievedShift, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllUsers() {
        List<Address> allShifts = addressService.getAllAddress();
        return new ResponseEntity<>(allShifts, HttpStatus.OK);
    }
}
