package com.example.springsecurityassignment.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    @GetMapping("/user/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>(" login successfull ", HttpStatus.CREATED);
    }
}
