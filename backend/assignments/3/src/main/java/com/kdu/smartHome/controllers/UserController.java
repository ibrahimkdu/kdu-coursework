package com.kdu.smartHome.controllers;

import com.kdu.smartHome.model.User;
import com.kdu.smartHome.service.UserService;
import com.kdu.smartHome.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User userRequest) {
        try {
            userService.registerUser(userRequest);
            // Generate authentication token
            String token = generateAuthToken(userRequest);
            return ResponseEntity.ok(Map.of("message", "User registered successfully", "token", token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Registration failed"));
        }
    }

    // Implement a method to generate authentication token
    private String generateAuthToken(User user) {
        String token = JwtUtil.generateToken(user);
        return token;
        // Your logic to generate authentication token
    }
}
